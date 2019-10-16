    public int payloadSize() {
        return getSizeInBytes() - headerSize(magic());
    }
