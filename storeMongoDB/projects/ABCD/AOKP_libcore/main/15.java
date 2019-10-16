    private String hash(X500Principal name) {
        int hash = NativeCrypto.X509_NAME_hash_old(name);
        return IntegralToString.intToHexString(hash, false, 8);
    }
