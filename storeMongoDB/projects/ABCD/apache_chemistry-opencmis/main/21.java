    public void addHeader(String header, String value) {
        if (header == null || header.trim().length() == 0) {
            return;
        }

        int x = 0;
        while (containsKey(SessionParameter.HEADER + "." + x)) {
            x++;
        }

        put(SessionParameter.HEADER + "." + x, header + ":" + value);
    }
