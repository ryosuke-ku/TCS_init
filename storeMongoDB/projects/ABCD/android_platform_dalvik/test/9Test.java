    public void test5_findFirst2() {
        for (int i = 0; i < 31; i++) {
            assertEquals(label(i), i + 1, Bits.findFirst(1 << (i + 1), i));
        }
    }
