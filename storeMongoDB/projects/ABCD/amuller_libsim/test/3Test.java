    public void min3int() {
        assertEquals(-10, MathFuncs.min3(-10, 5, 13));
        assertEquals(-13, MathFuncs.min3(-10, 5, -13));
        assertEquals(5, MathFuncs.min3(10, 5, 13));
    }
