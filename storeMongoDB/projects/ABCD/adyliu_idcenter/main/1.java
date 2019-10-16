    public static String encode(long number) {
        if (number < 0) throw new IllegalArgumentException("Number(Base62) must be positive: " + number);
        if (number == 0) return "0";
        StringBuilder buf = new StringBuilder();
        while (number != 0) {
            buf.append(digitsChar[(int) (number % BASE)]);
            number /= BASE;
        }
        return buf.reverse().toString();
    }
