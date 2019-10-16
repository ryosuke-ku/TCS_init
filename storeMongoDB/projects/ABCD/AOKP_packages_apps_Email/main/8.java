    public static String getTextFromPart(Part part) {
        try {
            if (part != null && part.getBody() != null) {
                InputStream in = part.getBody().getInputStream();
                String mimeType = part.getMimeType();
                if (mimeType != null && MimeUtility.mimeTypeMatches(mimeType, "text/*")) {
                    /*
                     * Now we read the part into a buffer for further processing. Because
                     * the stream is now wrapped we'll remove any transfer encoding at this point.
                     */
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    IOUtils.copy(in, out);
                    in.close();
                    in = null;      // we want all of our memory back, and close might not release

                    /*
                     * We've got a text part, so let's see if it needs to be processed further.
                     */
                    String charset = getHeaderParameter(part.getContentType(), "charset");
                    if (charset != null) {
                        /*
                         * See if there is conversion from the MIME charset to the Java one.
                         */
                        charset = CharsetUtil.toJavaCharset(charset);
                    }
                    /*
                     * No encoding, so use us-ascii, which is the standard.
                     */
                    if (charset == null) {
                        charset = "ASCII";
                    }
                    /*
                     * Convert and return as new String
                     */
                    String result = out.toString(charset);
                    out.close();
                    return result;
                }
            }

        }
        catch (OutOfMemoryError oom) {
            /*
             * If we are not able to process the body there's nothing we can do about it. Return
             * null and let the upper layers handle the missing content.
             */
            Log.e(Logging.LOG_TAG, "Unable to getTextFromPart " + oom.toString());
        }
        catch (Exception e) {
            /*
             * If we are not able to process the body there's nothing we can do about it. Return
             * null and let the upper layers handle the missing content.
             */
            Log.e(Logging.LOG_TAG, "Unable to getTextFromPart " + e.toString());
        }
        return null;
    }
