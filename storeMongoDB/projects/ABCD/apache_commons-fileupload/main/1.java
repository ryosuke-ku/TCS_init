    public Map<String, List<FileItem>> parseParameterMap(HttpServletRequest request)
            throws FileUploadException {
        return parseParameterMap(new ServletRequestContext(request));
    }
