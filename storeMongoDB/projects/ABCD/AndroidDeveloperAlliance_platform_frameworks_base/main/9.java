        public int read(byte buffer[], int offset, int count) throws IOException {
            if (offset < 0 || count < 0 || offset + count > buffer.length) {
                // readBytes() also does this check, but we need to do it before
                // changing count.
                throw new IndexOutOfBoundsException();
            }
            count = Math.min(count, available());
            if (count < 1) {
                return -1;
            }
            int result = readBytes(buffer, mOffset, offset, count);
            if (result > 0) {
                mOffset += result;
            }
            return result;
        }
