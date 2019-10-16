    public void testCollectParts() throws MessagingException, Exception {
        // golden cases; these will marked as attachments
        final String cid1 = "<i_12e8248b4f0874cb>";
        final Part cid1bp = MessageTestUtils.bodyPart("image/gif; name=\"im1.gif\"", cid1);
        final String cid2 = "<ii_12e8248b4f0874cb>";
        final Part cid2bp = MessageTestUtils.bodyPart("image/gif", cid2);
        cid2bp.addHeader(MimeHeader.HEADER_CONTENT_DISPOSITION, "inline; filename=\"im2.gif\"");
        final String cid3 = "<iii_12e8248b4f0874cb>";
        final Part cid3bp = MessageTestUtils.bodyPart("image/gif", cid3);
        cid3bp.addHeader(MimeHeader.HEADER_CONTENT_DISPOSITION, "attachment; filename=\"im3.gif\"");
        // error cases; these will NOT be marked as attachments
        final String cid4 = "<iv_12e8248b4f0874cb>";
        final Part cid4bp = MessageTestUtils.bodyPart("image/gif", cid4);  // no name attr
        final String cid5 = "<v_12e8248b4f0874cb>";
        final Part cid5bp = MessageTestUtils.bodyPart("image/gif", cid5);
        cid5bp.addHeader(MimeHeader.HEADER_CONTENT_DISPOSITION, "inline"); // no filename attr

        // Default content disposition
        final ArrayList<Part> view1 = new ArrayList<Part>();
        final ArrayList<Part> attach1 = new ArrayList<Part>();
        MimeUtility.collectParts(cid1bp, view1, attach1);
        assertEquals(1, attach1.size());
        assertEquals(attach1.get(0), cid1bp);

        // Explicit content disposition of "inline"
        final ArrayList<Part> view2 = new ArrayList<Part>();
        final ArrayList<Part> attach2 = new ArrayList<Part>();
        MimeUtility.collectParts(cid2bp, view2, attach2);
        assertEquals(1, attach2.size());
        assertEquals(attach2.get(0), cid2bp);

        // Explicit content disposition of "attachment"
        final ArrayList<Part> view3 = new ArrayList<Part>();
        final ArrayList<Part> attach3 = new ArrayList<Part>();
        MimeUtility.collectParts(cid3bp, view3, attach3);
        assertEquals(1, attach3.size());
        assertEquals(attach3.get(0), cid3bp);

        // Default content disposition; missing name attribute on content-type
        final ArrayList<Part> view4 = new ArrayList<Part>();
        final ArrayList<Part> attach4 = new ArrayList<Part>();
        MimeUtility.collectParts(cid4bp, view4, attach4);
        assertEquals(0, attach4.size());

        // Content disposition of "inline"; missing filename attribute
        final ArrayList<Part> view5 = new ArrayList<Part>();
        final ArrayList<Part> attach5 = new ArrayList<Part>();
        MimeUtility.collectParts(cid5bp, view5, attach5);
        assertEquals(0, attach5.size());
    }
