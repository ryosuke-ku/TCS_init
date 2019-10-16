    public void testPollWithEmptyUserAssignment() {
        KafkaConsumer<byte[], byte[]> consumer = newConsumer();
        consumer.assign(Collections.<TopicPartition>emptySet());
        try {
            consumer.poll(0);
        } finally {
            consumer.close();
        }
    }
