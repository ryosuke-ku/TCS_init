    public static int countPhoneNumberDigits(String query) {
        int numDigits = 0;
        int len = query.length();
        for (int i = 0; i < len; i++) {
            char c = query.charAt(i);
            if (Character.isDigit(c)) {
                numDigits ++;
            } else if (c == '*' || c == '#' || c == 'N' || c == '.' || c == ';'
                    || c == '-' || c == '(' || c == ')' || c == ' ') {
                // carry on
            } else if (c == '+' && numDigits == 0) {
                // plus before any digits is ok
            } else {
                return 0; // not a phone number
            }
        }
        return numDigits;
    }
