    public void testShortToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.shortToBinary((short)0x0000, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.shortToBinary((short)0x0000, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.shortToBinary((short)0x0000, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.shortToBinary((short)0xCDEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(
            new boolean[]{
                true, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 1));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 2));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 3));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 15));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 16));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 2, 1));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, true, true, true, false, true, true, true, true, false,
                true, true, false, false, true, true, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 2, 16));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, true, true, true, true, false, true, true, false,
                false, true, true, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 1, new boolean[21], 0, 15));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, true, true, true, true, false, true, true, false, false,
                true, true, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 2, new boolean[21], 0, 14));
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // true, true, false, true, true, false, false, true, false, false, false, false,
        // false},Conversion.shortToBinary((short)0xCDEF, 2,new boolean[21],
        // 3,15));//rejected by
        // assertion
        assertBinaryEquals(
            new boolean[]{
                false, false, false, true, true, false, true, true, true, true, false, true,
                true, false, false, true, true, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 2, new boolean[21], 3, 14));
    }
