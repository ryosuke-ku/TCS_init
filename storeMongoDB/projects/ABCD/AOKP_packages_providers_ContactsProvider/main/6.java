    public static void escapeLikeValue(StringBuilder sb, String value, char escapeChar) {
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (ch == '%' || ch == '_') {
                sb.append(escapeChar);
            }
            sb.append(ch);
        }
    }
