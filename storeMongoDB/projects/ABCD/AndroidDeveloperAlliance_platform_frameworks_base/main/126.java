        String getEncoded() {
            @SuppressWarnings("StringEquality")
            boolean hasEncoded = encoded != NOT_CACHED;

            // Don't encode '/'.
            return hasEncoded ? encoded : (encoded = encode(decoded, "/"));
        }
