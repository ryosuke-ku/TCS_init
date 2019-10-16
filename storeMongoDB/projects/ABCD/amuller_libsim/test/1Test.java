    public void max4float() {
        assertEquals(36.9f, MathFuncs.max4(10.1f, 5.45f, -3.12f, 36.9f));
        assertEquals(10.1f, MathFuncs.max4(10.1f, 5.45f, -3.12f, 6.9f));
        assertEquals(-3.12f, MathFuncs.max4(-10.1f, -5.45f, -3.12f, -36.9f));
        assertEquals(25.4f, MathFuncs.max4(10.1f, 25.45f, -3.12f, 16.9f));
    }
