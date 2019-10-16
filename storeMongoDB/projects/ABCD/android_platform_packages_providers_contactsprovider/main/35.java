    public static int countPhoneNumberDigits(String query) {
        int numDigits = 0;
        int len = query.length();
        for (int i = 0; i < len; i++) {
            char c = query.charAt(i);
            if (Character.isDigit(c)) {
                numDigits ++;
            } else if (c == '*' || c == '#' || c == 'N' || c == '.' || c == ';'
                    || c == '-' || c == '(' || c == ')' || c == ' ') {
                // Carry on.
            } else if (c == '+' && numDigits == 0) {
                // Plus sign before any digits is OK.
            } else {
                return 0;  // Not a phone number.
            }
        }
        return numDigits;
    }
