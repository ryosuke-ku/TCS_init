    public int read(char[] buffer, int offset, int length) throws IOException {
        synchronized (lock) {
            if (!isOpen()) {
                throw new IOException("InputStreamReader is closed");
            }

            Arrays.checkOffsetAndCount(buffer.length, offset, length);
            if (length == 0) {
                return 0;
            }

            CharBuffer out = CharBuffer.wrap(buffer, offset, length);
            CoderResult result = CoderResult.UNDERFLOW;

            // bytes.remaining() indicates number of bytes in buffer
            // when 1-st time entered, it'll be equal to zero
            boolean needInput = !bytes.hasRemaining();

            while (out.hasRemaining()) {
                // fill the buffer if needed
                if (needInput) {
                    try {
                        if (in.available() == 0 && out.position() > offset) {
                            // we could return the result without blocking read
                            break;
                        }
                    } catch (IOException e) {
                        // available didn't work so just try the read
                    }

                    int desiredByteCount = bytes.capacity() - bytes.limit();
                    int off = bytes.arrayOffset() + bytes.limit();
                    int actualByteCount = in.read(bytes.array(), off, desiredByteCount);

                    if (actualByteCount == -1) {
                        endOfInput = true;
                        break;
                    } else if (actualByteCount == 0) {
                        break;
                    }
                    bytes.limit(bytes.limit() + actualByteCount);
                    needInput = false;
                }

                // decode bytes
                result = decoder.decode(bytes, out, false);

                if (result.isUnderflow()) {
                    // compact the buffer if no space left
                    if (bytes.limit() == bytes.capacity()) {
                        bytes.compact();
                        bytes.limit(bytes.position());
                        bytes.position(0);
                    }
                    needInput = true;
                } else {
                    break;
                }
            }

            if (result == CoderResult.UNDERFLOW && endOfInput) {
                result = decoder.decode(bytes, out, true);
                decoder.flush(out);
                decoder.reset();
            }
            if (result.isMalformed() || result.isUnmappable()) {
                result.throwException();
            }

            return out.position() - offset == 0 ? -1 : out.position() - offset;
        }
    }
