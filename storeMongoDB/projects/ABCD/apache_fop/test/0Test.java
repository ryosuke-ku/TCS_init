    protected void streamToFile(byte[] bytes, String filename) throws IOException {
        OutputStream output = new FileOutputStream(filename);
        output.write(bytes);
        output.close();
    }
