    public void testWithNullContentType() {
        FileUploadBase fu = new DiskFileUpload();

        HttpServletRequest req = HttpServletRequestFactory.createHttpServletRequestWithNullContentType();

        try {
            fu.parseRequest(req);
            fail("testWithNullContentType: expected exception was not thrown");
        } catch (DiskFileUpload.InvalidContentTypeException expected) {
            // this exception is expected
        } catch (FileUploadException unexpected) {
            fail("testWithNullContentType: unexpected exception was thrown");
        }
    }
