    public Map readFile(String fileName) throws Exception {
        if (fileName.toUpperCase().endsWith("CSV")) {
            FileInputStream stream = new FileInputStream(fileName);
            readCSV(new FileInputStream(fileName));
            stream.close();
        }
        return dome;
    }
