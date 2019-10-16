    public void testByteToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.byteToBinary((byte)0xEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 1));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 2));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 3));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, true, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 7));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, true, false, false, true, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 8));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 2, 1));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, true, false, true, false, false, true, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 2, 8));
        assertBinaryEquals(new boolean[]{
            false, true, false, true, false, false, true, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 1, new boolean[13], 0, 7));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, false, true, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 2, new boolean[13], 0, 6));
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // false, false, false, false, false},Conversion.byteToBinary((byte)0x95, 2,new
        // boolean[13], 3, 7));//rejected by assertion
        assertBinaryEquals(new boolean[]{
            false, false, false, true, false, true, false, false, true, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 2, new boolean[13], 3, 6));
    }
