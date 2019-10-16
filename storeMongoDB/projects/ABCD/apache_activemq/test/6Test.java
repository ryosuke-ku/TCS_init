    public void testResetReadsNextAMQPHeaderMidParse() throws Exception {
        AmqpHeader inputHeader = new AmqpHeader();

        DataByteArrayOutputStream headers = new DataByteArrayOutputStream();
        headers.write(inputHeader.getBuffer());
        headers.write(inputHeader.getBuffer());
        headers.write(inputHeader.getBuffer());
        headers.close();

        codec = new AmqpFrameParser(new AmqpFrameParser.AMQPFrameSink() {

            @Override
            public void onFrame(Object frame) {
                frames.add(frame);
                codec.reset();
            }
        });

        codec.parse(headers.toBuffer().toByteBuffer());

        assertEquals(3, frames.size());
        for (Object header : frames) {
            assertTrue(header instanceof AmqpHeader);
            AmqpHeader outputHeader = (AmqpHeader) header;
            assertHeadersEqual(inputHeader, outputHeader);
        }
    }
