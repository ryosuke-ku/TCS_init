        public void parse(ByteBuffer incoming) throws IOException {
            int length = Math.min(incoming.remaining(), frame.getLength() - frame.offset);
            incoming.get(frame.data, frame.offset, length);
            frame.offset += length;

            if (frame.offset == frame.length) {
                LOG.trace("Contents of size {} have been read", frame.length);
                frame.reset();
                frameSink.onFrame(frame);
                if (currentParser == this) {
                    currentParser = initializeFrameLengthParser();
                }
                if (incoming.hasRemaining()) {
                    currentParser.parse(incoming);
                }
            }
        }
