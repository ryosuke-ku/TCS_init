    public boolean isValid() {
        return checksum() == Utils.crc32(buffer.array(), buffer.position() + buffer.arrayOffset() + payloadOffset(magic()), payloadSize());
    }
