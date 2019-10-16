    public boolean equals(Object other) {
        if (!(other instanceof VerifierDeviceIdentity)) {
            return false;
        }

        final VerifierDeviceIdentity o = (VerifierDeviceIdentity) other;
        return mIdentity == o.mIdentity;
    }
