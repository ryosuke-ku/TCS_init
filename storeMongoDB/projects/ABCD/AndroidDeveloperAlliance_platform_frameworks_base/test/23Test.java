    public void testCompareTo() {
        Uri a = Uri.parse("foo:a");
        Uri b = Uri.parse("foo:b");
        Uri b2 = Uri.parse("foo:b");

        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, b.compareTo(b2));
    }
