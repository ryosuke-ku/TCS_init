    public static int width(long value) {
        int msb = 0;

        while (value >= 1l << 16) {
            value >>= 16;
            msb += 16;
        }

        while (value >= 1l << 4) {
            value >>= 4;
            msb += 4;
        }

        while (value >= 1) {
            value >>= 1;
            msb += 1;
        }

        return msb;
    }
