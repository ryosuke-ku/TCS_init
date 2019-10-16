    private static String alias(boolean user, X509Certificate x, int index) {
        String prefix = user ? "user:" : "system:";

        X500Principal subject = x.getSubjectX500Principal();
        int intHash = NativeCrypto.X509_NAME_hash_old(subject);
        String strHash = IntegralToString.intToHexString(intHash, false, 8);

        return prefix + strHash + '.' + index;
    }
