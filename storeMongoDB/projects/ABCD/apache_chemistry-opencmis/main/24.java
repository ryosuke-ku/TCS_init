    public void load(InputStream stream) throws IOException {
        if (stream == null) {
            throw new IllegalArgumentException("Stream must be set!");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF8"));

        String line;
        while ((line = reader.readLine()) != null) {
            putLine(line);
        }
    }
