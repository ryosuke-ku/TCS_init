        void add(String segment) {
            if (segments == null) {
                segments = new String[4];
            } else if (size + 1 == segments.length) {
                String[] expanded = new String[segments.length * 2];
                System.arraycopy(segments, 0, expanded, 0, segments.length);
                segments = expanded;
            }

            segments[size++] = segment;
        }
