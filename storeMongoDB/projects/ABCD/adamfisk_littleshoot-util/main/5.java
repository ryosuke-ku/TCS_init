    public static long byteArrayToLong(byte[] b, int offset)
        {
        return ((b[0 + offset] & 0xffL) << 56)
             | ((b[1 + offset] & 0xffL) << 48)
             | ((b[2 + offset] & 0xffL) << 40)
             | ((b[3 + offset] & 0xffL) << 32)
             | ((b[4 + offset] & 0xffL) << 24)
             | ((b[5 + offset] & 0xffL) << 16)
             | ((b[6 + offset] & 0xffL) << 8)
             |  (b[7 + offset] & 0xffL);
        }
