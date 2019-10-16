    public void store(final OutputStream stream) throws IOException {
        if (stream == null) {
            throw new IllegalArgumentException("Stream must be set!");
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));

        List<String> keys = new ArrayList<String>(keySet());
        Collections.sort(keys);

        for (String key : keys) {
            String value = get(key);
            if (value == null) {
                value = "";
            }

            writer.write(key);
            writer.write('=');
            writer.write(value);
            writer.newLine();
        }

        writer.flush();
    }
