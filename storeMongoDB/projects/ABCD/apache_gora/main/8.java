  public byte[] encodeFloat(float f, byte[] ret) throws IOException {
    int i = Float.floatToRawIntBits(f);
    if(i < 0)
      i = ~i;
    else
      i = i ^ 0x80000000;

    return super.encodeInt(i, ret);

  }
