    public void testIntToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.intToBinary(0x90ABCDEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 1));
        assertBinaryEquals(new boolean[]{
            true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 2));
        assertBinaryEquals(new boolean[]{
            true, true, true, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 3));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 31));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, true, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 32));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 1));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, true, true, true, false, true, true, true, true, false,
                true, true, false, false, true, true, true, true, false, true, false, true,
                false, true, false, false, false, false, true, false, false, true, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 32));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, true, true, true, true, false, true, true, false,
                false, true, true, true, true, false, true, false, true, false, true, false,
                false, false, false, true, false, false, true, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 1, new boolean[37], 0, 31));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, true, true, true, true, false, true, true, false, false,
                true, true, true, true, false, true, false, true, false, true, false, false,
                false, false, true, false, false, true, false, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 0, 30));
        // assertBinaryEquals(new boolean[]{false, false, false, true, true, false, true,
        // true,
        // true, true, false, true, true, false, false, true, true, true, true, false, true,
        // false, true, false, true, false, false, false, false, true, false, false, false,
        // false, false, false, false},Conversion.intToBinary(0x90ABCDEF, 2,new boolean[37],
        // 3,31));//rejected by assertion
        assertBinaryEquals(
            new boolean[]{
                false, false, false, true, true, false, true, true, true, true, false, true,
                true, false, false, true, true, true, true, false, true, false, true, false,
                true, false, false, false, false, true, false, false, true, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 3, 30));
    }
