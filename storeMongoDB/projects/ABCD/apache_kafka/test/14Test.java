    public void testPause() {
        KafkaConsumer<byte[], byte[]> consumer = newConsumer();

        consumer.assign(singletonList(tp0));
        assertEquals(singleton(tp0), consumer.assignment());
        assertTrue(consumer.paused().isEmpty());

        consumer.pause(singleton(tp0));
        assertEquals(singleton(tp0), consumer.paused());

        consumer.resume(singleton(tp0));
        assertTrue(consumer.paused().isEmpty());

        consumer.unsubscribe();
        assertTrue(consumer.paused().isEmpty());

        consumer.close();
    }
