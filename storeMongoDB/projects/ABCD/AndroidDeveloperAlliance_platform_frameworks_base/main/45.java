    public static Uri fromParts(String scheme, String ssp,
            String fragment) {
        if (scheme == null) {
            throw new NullPointerException("scheme");
        }
        if (ssp == null) {
            throw new NullPointerException("ssp");
        }

        return new OpaqueUri(scheme, Part.fromDecoded(ssp),
                Part.fromDecoded(fragment));
    }
