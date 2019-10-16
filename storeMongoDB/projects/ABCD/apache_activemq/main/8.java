        public void reset(int nextExpectedReadSize) {
            // Allocate a new Buffer to hold the incoming frame.  We must write
            // back the frame size value before continue on to read the indicated
            // frame size minus the size of the AMQP frame size header value.
            frame = new Buffer(nextExpectedReadSize);
            frame.bigEndianEditor().writeInt(nextExpectedReadSize);

            // Reset the length to total length as we do direct write after this.
            frame.length = frame.data.length;
        }
