    public List<String> getQueryParameters(String key) {
        if (isOpaque()) {
            throw new UnsupportedOperationException(NOT_HIERARCHICAL);
        }
        if (key == null) {
          throw new NullPointerException("key");
        }

        String query = getEncodedQuery();
        if (query == null) {
            return Collections.emptyList();
        }

        String encodedKey;
        try {
            encodedKey = URLEncoder.encode(key, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }

        ArrayList<String> values = new ArrayList<String>();

        int start = 0;
        do {
            int nextAmpersand = query.indexOf('&', start);
            int end = nextAmpersand != -1 ? nextAmpersand : query.length();

            int separator = query.indexOf('=', start);
            if (separator > end || separator == -1) {
                separator = end;
            }

            if (separator - start == encodedKey.length()
                    && query.regionMatches(start, encodedKey, 0, encodedKey.length())) {
                if (separator == end) {
                  values.add("");
                } else {
                  values.add(decode(query.substring(separator + 1, end)));
                }
            }

            // Move start to end of name.
            if (nextAmpersand != -1) {
                start = nextAmpersand + 1;
            } else {
                break;
            }
        } while (true);

        return Collections.unmodifiableList(values);
    }
