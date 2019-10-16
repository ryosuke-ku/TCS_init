    public long checksum() {
        return Utils.getUnsignedInt(buffer, crcOffset(magic()));
    }
