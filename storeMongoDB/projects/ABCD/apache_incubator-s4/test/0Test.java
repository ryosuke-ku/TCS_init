    public void testSerDeser() {

        ZNRecordSerializer serializer = new ZNRecordSerializer();
        ZNRecord znRecord = new ZNRecord("test");
        byte[] serialize = serializer.serialize(znRecord);
        System.out.println(new String(serialize));

        ZNRecord newZNRecord = (ZNRecord) serializer.deserialize(serialize);
        System.out.println(newZNRecord.getId());
        Assert.assertEquals(znRecord, newZNRecord);
    }
