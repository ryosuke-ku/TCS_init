    public void simpleWriterTest() throws Exception {
        resource = this.getClass().getResource("/Machakos_csv.zip");
        Map data = reader.readFile(resource.getPath());
        log.info("Translation results: {}", data.toString());
        writer.writeFile("output", data);
        ArrayList<File> outputs = writer.getOutputWthFiles();
        HashSet expecetedNames = new HashSet();
        expecetedNames.add("MK07.csv");
        expecetedNames.add("MK10.csv");
        expecetedNames.add("MK13.csv");
        expecetedNames.add("MK14.csv");
        assertTrue(expecetedNames.contains(outputs.get(0).getName()));
        assertTrue(expecetedNames.contains(outputs.get(1).getName()));
        assertTrue(expecetedNames.contains(outputs.get(2).getName()));
        assertTrue(expecetedNames.contains(outputs.get(3).getName()));
        assertTrue(outputs.get(0).delete());
        assertTrue(outputs.get(1).delete());
        assertTrue(outputs.get(2).delete());
        assertTrue(outputs.get(3).delete());
        new File("output").delete();
    }
