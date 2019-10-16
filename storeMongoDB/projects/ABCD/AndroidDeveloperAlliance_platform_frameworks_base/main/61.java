        public String getLastPathSegment() {
            // TODO: If we haven't parsed all of the segments already, just
            // grab the last one directly so we only allocate one string.

            List<String> segments = getPathSegments();
            int size = segments.size();
            if (size == 0) {
                return null;
            }
            return segments.get(size - 1);
        }
