    public static VerifierDeviceIdentity parse(String deviceIdentity)
            throws IllegalArgumentException {
        final byte[] input;
        try {
            input = deviceIdentity.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("bad base-32 characters in input");
        }

        return new VerifierDeviceIdentity(decodeBase32(input));
    }
