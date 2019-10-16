    public static RequestMethod extractMethod(final HttpServletRequest httpServletRequest) {
        return RequestMethod.valueOf(httpServletRequest.getMethod());
    }
