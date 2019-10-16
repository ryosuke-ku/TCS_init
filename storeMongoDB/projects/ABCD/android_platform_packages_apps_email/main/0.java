        public Date parse(String date) throws ParseException {
            return super.get().parse(date);
        }
tive.
        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isAsciiLetter(c) || isAsciiNumber(c)
                    || ('-' == c) || ('.' == c)) {
                // Safe - use as is.
                sb.append(c);
            } else if ('+' == c) {
                // + is used as our escape character, so double it up.
                sb.append("++");
            } else {
                // Unsafe - escape.
                sb.append('+').append((int) c);
            }
        }
        return sb.toString();
    }
