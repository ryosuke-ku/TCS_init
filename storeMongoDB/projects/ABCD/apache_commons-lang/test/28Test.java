    public void testConstMethods() {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        assertTrue("CONST(boolean)", ObjectUtils.CONST(true));
        assertEquals("CONST(byte)", (byte) 3, ObjectUtils.CONST((byte) 3));
        assertEquals("CONST(char)", (char) 3, ObjectUtils.CONST((char) 3));
        assertEquals("CONST(short)", (short) 3, ObjectUtils.CONST((short) 3));
        assertEquals("CONST(int)", 3, ObjectUtils.CONST(3));
        assertEquals("CONST(long)", 3l, ObjectUtils.CONST(3l));
        assertEquals("CONST(float)", 3f, ObjectUtils.CONST(3f), 0);
        assertEquals("CONST(double)", 3.0, ObjectUtils.CONST(3.0), 0);
        assertEquals("CONST(Object)", "abc", ObjectUtils.CONST("abc"));

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertTrue(MAGIC_FLAG);
        assertEquals(127, MAGIC_BYTE1);
        assertEquals(127, MAGIC_BYTE2);
        assertEquals('a', MAGIC_CHAR);
        assertEquals(123, MAGIC_SHORT1);
        assertEquals(127, MAGIC_SHORT2);
        assertEquals(123, MAGIC_INT);
        assertEquals(123, MAGIC_LONG1);
        assertEquals(3, MAGIC_LONG2);
        assertEquals(1.0f, MAGIC_FLOAT, 0.0f);
        assertEquals(1.0, MAGIC_DOUBLE, 0.0);
        assertEquals("abc", MAGIC_STRING);

        try {
            ObjectUtils.CONST_BYTE(-129);
            fail("CONST_BYTE(-129): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_BYTE(128);
            fail("CONST_BYTE(128): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_SHORT(-32769);
            fail("CONST_SHORT(-32769): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_BYTE(32768);
            fail("CONST_SHORT(32768): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }

    }
