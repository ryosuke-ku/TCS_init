    public void testSend() {
        Properties mainProperties = new Properties();
        mainProperties.put("http.port","9093");
        Jafka jafka = createJafka(mainProperties);
        Properties producerConfig = new Properties();
        producerConfig.setProperty("broker.list", "0:localhost:"+jafka.getPort());
        producerConfig.setProperty("serializer.class", StringEncoder.class.getName());
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(producerConfig));
        for (int i = 0; i < 1000; i++) {
            producer.send(new StringProducerData("demo").add("Hello jafka").add("https://github.com/adyliu/jafka"));
        }
        producer.close();
        ////////////////////////////////////////////////
        close(jafka);
    }
