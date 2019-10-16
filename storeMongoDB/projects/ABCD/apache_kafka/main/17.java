    private void close(boolean swallowException) {
        log.trace("Closing the Kafka consumer.");
        AtomicReference<Throwable> firstException = new AtomicReference<>();
        this.closed = true;
        ClientUtils.closeQuietly(coordinator, "coordinator", firstException);
        ClientUtils.closeQuietly(interceptors, "consumer interceptors", firstException);
        ClientUtils.closeQuietly(metrics, "consumer metrics", firstException);
        ClientUtils.closeQuietly(client, "consumer network client", firstException);
        ClientUtils.closeQuietly(keyDeserializer, "consumer key deserializer", firstException);
        ClientUtils.closeQuietly(valueDeserializer, "consumer value deserializer", firstException);
        AppInfoParser.unregisterAppInfo(JMX_PREFIX, clientId);
        log.debug("The Kafka consumer has closed.");
        if (firstException.get() != null && !swallowException) {
            throw new KafkaException("Failed to close kafka consumer", firstException.get());
        }
    }
