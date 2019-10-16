    public void testWakeupWithFetchDataAvailable() {
        int rebalanceTimeoutMs = 60000;
        int sessionTimeoutMs = 30000;
        int heartbeatIntervalMs = 3000;

        // adjust auto commit interval lower than heartbeat so we don't need to deal with
        // a concurrent heartbeat request
        int autoCommitIntervalMs = 1000;

        Time time = new MockTime();
        Cluster cluster = TestUtils.singletonCluster(topic, 1);
        Node node = cluster.nodes().get(0);

        Metadata metadata = new Metadata(0, Long.MAX_VALUE);
        metadata.update(cluster, time.milliseconds());

        MockClient client = new MockClient(time, metadata);
        client.setNode(node);
        PartitionAssignor assignor = new RoundRobinAssignor();

        final KafkaConsumer<String, String> consumer = newConsumer(time, client, metadata, assignor,
                rebalanceTimeoutMs, sessionTimeoutMs, heartbeatIntervalMs, true, autoCommitIntervalMs);

        consumer.subscribe(Arrays.asList(topic), getConsumerRebalanceListener(consumer));
        prepareRebalance(client, node, assignor, Arrays.asList(tp0), null);

        consumer.poll(0);

        // respond to the outstanding fetch so that we have data available on the next poll
        client.respondFrom(fetchResponse(tp0, 0, 5), node);
        client.poll(0, time.milliseconds());

        consumer.wakeup();

        try {
            consumer.poll(0);
            fail();
        } catch (WakeupException e) {
        }

        // make sure the position hasn't been updated
        assertEquals(0, consumer.position(tp0));

        // the next poll should return the completed fetch
        ConsumerRecords<String, String> records = consumer.poll(0);
        assertEquals(5, records.count());
    }
