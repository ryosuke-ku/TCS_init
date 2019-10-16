    public void testComparisonMethod()
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        byte[] commonBytes = new byte[10];
        byte[] aBytes = new byte[16];
        byte[] bBytes = new byte[16];
        for (int i = 0 ; i < 100000 ; i++)
        {
            int commonLength = random.nextInt(0, 10);
            random.nextBytes(commonBytes);
            random.nextBytes(aBytes);
            random.nextBytes(bBytes);
            System.arraycopy(commonBytes, 0, aBytes, 0, commonLength);
            System.arraycopy(commonBytes, 0, bBytes, 0, commonLength);
            int aLength = random.nextInt(commonLength, 16);
            int bLength = random.nextInt(commonLength, 16);
            ColumnIdentifier a = new ColumnIdentifier(ByteBuffer.wrap(aBytes, 0, aLength), BytesType.instance);
            ColumnIdentifier b = new ColumnIdentifier(ByteBuffer.wrap(bBytes, 0, bLength), BytesType.instance);
            Assert.assertEquals("" + i, compareResult(a.compareTo(b)), compareResult(ByteBufferUtil.compareUnsigned(a.bytes, b.bytes)));
        }
    }
