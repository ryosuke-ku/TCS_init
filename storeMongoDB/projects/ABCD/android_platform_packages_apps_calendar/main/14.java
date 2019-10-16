    public static String extractDomain(String email) {
        int separator = email.lastIndexOf('@');
        if (separator != -1 && ++separator < email.length()) {
            return email.substring(separator);
        }
        return null;
    }
