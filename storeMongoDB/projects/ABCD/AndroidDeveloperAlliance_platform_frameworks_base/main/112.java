        public Builder appendQueryParameter(String key, String value) {
            // This URI will be hierarchical.
            this.opaquePart = null;

            String encodedParameter = encode(key, null) + "="
                    + encode(value, null);

            if (query == null) {
                query = Part.fromEncoded(encodedParameter);
                return this;
            }

            String oldQuery = query.getEncoded();
            if (oldQuery == null || oldQuery.length() == 0) {
                query = Part.fromEncoded(encodedParameter);
            } else {
                query = Part.fromEncoded(oldQuery + "&" + encodedParameter);
            }

            return this;
        }
