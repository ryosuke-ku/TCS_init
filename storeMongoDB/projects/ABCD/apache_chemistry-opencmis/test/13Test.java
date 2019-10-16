    public void testFileContentStream() throws IOException {
        File tmpFile = File.createTempFile("test", ".txt");

        FileOutputStream fos = new FileOutputStream(tmpFile);
        fos.write(CONTENT_BYTES);
        fos.close();

        MutableContentStream contentStream = ContentStreamUtils.createFileContentStream(tmpFile);

        assertNotNull(contentStream);
        assertEquals(tmpFile.getName(), contentStream.getFileName());
        assertTrue(contentStream.getMimeType().toLowerCase(Locale.ENGLISH).startsWith("text/plain"));
        assertEquals(CONTENT_BYTES.length, contentStream.getLength());
        assertNotNull(contentStream.getStream());
        assertTrue(contentStream.getStream() instanceof ContentStreamUtils.AutoCloseInputStream);

        AutoCloseInputStream in = (AutoCloseInputStream) contentStream.getStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        IOUtils.copy(in, out);

        assertArrayEquals(CONTENT_BYTES, out.toByteArray());

        try {
            in.read();
            fail();
        } catch (IOException ioe) {
            // excpeted
        }

        in.closeQuietly();
    }
