    private ProducerPoolData<V> create(ProducerData<K, V> pd) {
        Collection<Partition> topicPartitionsList = getPartitionListForTopic(pd);
        //FIXME: random Broker???
        int randomBrokerId = random.nextInt(topicPartitionsList.size());
        final Partition brokerIdPartition = new ArrayList<Partition>(topicPartitionsList).get(randomBrokerId);
        return this.producerPool.getProducerPoolData(pd.getTopic(),//
                new Partition(brokerIdPartition.brokerId, ProducerRequest.RandomPartition), pd.getData());
    }
