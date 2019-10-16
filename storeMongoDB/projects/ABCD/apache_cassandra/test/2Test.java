    public void testInternedCache()
    {
        AbstractType<?> utf8Type = UTF8Type.instance;
        AbstractType<?> bytesType = BytesType.instance;

        byte[] bytes = new byte [] { 0x63, (byte) 0x32 };
        String text = "c2"; // the UTF-8 encoding of this string is the same as bytes, 0x630x32

        ColumnIdentifier c1 = ColumnIdentifier.getInterned(ByteBuffer.wrap(bytes), bytesType);
        ColumnIdentifier c2 = ColumnIdentifier.getInterned(utf8Type, utf8Type.fromString(text), text);
        ColumnIdentifier c3 = ColumnIdentifier.getInterned(text, true);

        Assert.assertTrue(c1.isInterned());
        Assert.assertTrue(c2.isInterned());
        Assert.assertTrue(c3.isInterned());

        Assert.assertEquals("6332", c1.toString());
        Assert.assertEquals(text, c2.toString());
        Assert.assertEquals(text, c3.toString());
    }
