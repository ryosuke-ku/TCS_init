    public void testSerialization() throws Exception {
        SerializedImpl si = new SerializedImpl();
        si.add(new QName("lp0"));
        si.add(new QName("parentLp0"), new QName("lp1"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(si);
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        si = (SerializedImpl)ois.readObject();
        assertTrue(si.contains(new QName("lp0")));
        assertTrue(si.contains(new QName("parentLp0"), new QName("lp1")));
    }
