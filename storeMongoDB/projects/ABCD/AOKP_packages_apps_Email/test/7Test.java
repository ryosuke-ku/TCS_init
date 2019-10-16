    public void testFindPartByContentIdTestCase() throws MessagingException, Exception {
        final String cid1 = "cid.1@android.com";
        final Part cid1bp = MessageTestUtils.bodyPart("image/gif", cid1);
        final String cid2 = "cid.2@android.com";
        final Part cid2bp = MessageTestUtils.bodyPart("image/gif", "<" + cid2 + ">");

        final Message msg1 = new MessageBuilder()
            .setBody(new MultipartBuilder("multipart/related")
                 .addBodyPart(MessageTestUtils.bodyPart("text/html", null))
                 .addBodyPart((BodyPart)cid1bp)
                 .build())
            .build();
        // found cid1 part
        final Part actual1_1 = MimeUtility.findPartByContentId(msg1, cid1);
        assertEquals("could not found expected content-id part", cid1bp, actual1_1);

        final Message msg2 = new MessageBuilder()
            .setBody(new MultipartBuilder("multipart/mixed")
                .addBodyPart(MessageTestUtils.bodyPart("image/tiff", "cid.4@android.com"))
                .addBodyPart(new MultipartBuilder("multipart/related")
                    .addBodyPart(new MultipartBuilder("multipart/alternative")
                        .addBodyPart(MessageTestUtils.bodyPart("text/plain", null))
                        .addBodyPart(MessageTestUtils.bodyPart("text/html", null))
                        .buildBodyPart())
                    .addBodyPart((BodyPart)cid1bp)
                    .buildBodyPart())
                .addBodyPart(MessageTestUtils.bodyPart("image/gif", "cid.3@android.com"))
                .addBodyPart((BodyPart)cid2bp)
                .build())
            .build();
        // found cid1 part
        final Part actual2_1 = MimeUtility.findPartByContentId(msg2, cid1);
        assertEquals("found part from related multipart", cid1bp, actual2_1);

        // found cid2 part
        final Part actual2_2 = MimeUtility.findPartByContentId(msg2, cid2);
        assertEquals("found part from mixed multipart", cid2bp, actual2_2);
    }
