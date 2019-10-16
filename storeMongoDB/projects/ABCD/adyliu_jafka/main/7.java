    public CompressionCodec compressionCodec() {
        byte magicByte = magic();
        switch (magicByte) {
            case 0:
                return CompressionCodec.NoCompressionCodec;
            case 1:
                return CompressionCodec.valueOf(buffer.get(ATTRIBUTE_OFFSET) & CompressionCodeMask);
        }
        throw new RuntimeException("Invalid magic byte " + magicByte);
    }
