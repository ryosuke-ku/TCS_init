  public float decodeFloat(byte[] a) throws IOException{
    int i = super.decodeInt(a);
    if(i < 0)
      i = i ^ 0x80000000;
    else
      i = ~i;
    return Float.intBitsToFloat(i);
  }
