    public static void collectParts(Part part, ArrayList<Part> viewables,
            ArrayList<Part> attachments) throws MessagingException {
        String disposition = part.getDisposition();
        String dispositionType = null;
        String dispositionFilename = null;
        if (disposition != null) {
            dispositionType = MimeUtility.getHeaderParameter(disposition, null);
            dispositionFilename = MimeUtility.getHeaderParameter(disposition, "filename");
        }
        // An attachment filename can be defined in either the Content-Disposition header
        // or the Content-Type header. Content-Disposition is preferred, so we only try
        // the Content-Type header as a last resort.
        if (dispositionFilename == null) {
            String contentType = part.getContentType();
            dispositionFilename = MimeUtility.getHeaderParameter(contentType, "name");
        }
        boolean attachmentDisposition = "attachment".equalsIgnoreCase(dispositionType);
        // If a disposition is not specified, default to "inline"
        boolean inlineDisposition = dispositionType == null
                || "inline".equalsIgnoreCase(dispositionType);

        // A guess that this part is intended to be an attachment
        boolean attachment = attachmentDisposition
                || (dispositionFilename != null && !inlineDisposition);

        // A guess that this part is intended to be an inline.
        boolean inline = inlineDisposition && (dispositionFilename != null);

        // One or the other
        boolean attachmentOrInline = attachment || inline;

        if (part.getBody() instanceof Multipart) {
            // If the part is Multipart but not alternative it's either mixed or
            // something we don't know about, which means we treat it as mixed
            // per the spec. We just process its pieces recursively.
            MimeMultipart mp = (MimeMultipart)part.getBody();
            boolean foundHtml = false;
            if (mp.getSubTypeForTest().equals("alternative")) {
                for (int i = 0; i < mp.getCount(); i++) {
                    if (mp.getBodyPart(i).isMimeType("text/html")) {
                        foundHtml = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < mp.getCount(); i++) {
                // See if we have text and html
                BodyPart bp = mp.getBodyPart(i);
                // If there's html, don't bother loading text
                if (foundHtml && bp.isMimeType("text/plain")) {
                    continue;
                }
                collectParts(bp, viewables, attachments);
            }
        } else if (part.getBody() instanceof Message) {
            // If the part is an embedded message we just continue to process
            // it, pulling any viewables or attachments into the running list.
            Message message = (Message)part.getBody();
            collectParts(message, viewables, attachments);
        } else if ((!attachmentOrInline) && ("text/html".equalsIgnoreCase(part.getMimeType()))) {
            // If the part is HTML and we got this far, it's a viewable part of a mixed
            viewables.add(part);
        } else if ((!attachmentOrInline) && ("text/plain".equalsIgnoreCase(part.getMimeType()))) {
            // If the part is text and we got this far, it's a viewable part of a mixed
            viewables.add(part);
        } else if (attachmentOrInline) {
            // Finally, if it's an attachment or an inline we will include it as an attachment.
            attachments.add(part);
        }
    }
