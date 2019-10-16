    public static int findFirst(int value, int idx) {
        value &= ~((1 << idx) - 1); // Mask off too-low bits.
        int result = Integer.numberOfTrailingZeros(value);
        return (result == 32) ? -1 : result;
    }
