    public void testTextContentStream() throws IOException {
        MutableContentStream contentStream = ContentStreamUtils.createTextContentStream("filename", CONTENT);

        assertNotNull(contentStream);
        assertEquals("filename", contentStream.getFileName());
        assertEquals("text/plain; charset=UTF-8", contentStream.getMimeType());
        assertEquals(CONTENT_BYTES.length, contentStream.getLength());
        assertNotNull(contentStream.getStream());
        assertTrue(contentStream.getStream() instanceof ContentStreamUtils.AutoCloseInputStream);

        AutoCloseInputStream in = (AutoCloseInputStream) contentStream.getStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        if (in.markSupported()) {
            in.mark(1024);
            in.read();
            in.reset();
        }

        assertEquals(CONTENT_BYTES.length, in.available());

        IOUtils.copy(in, out);

        assertArrayEquals(CONTENT_BYTES, out.toByteArray());

        try {
            in.read();
            fail();
        } catch (IOException ioe) {
            // excpeted
        }

        assertFalse(in.markSupported());
    }
