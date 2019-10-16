    public void close() throws IOException, InterruptedException {
        super.close();

        if (client != null) {
            client.close();
        }
        if (node != null) {
            node.close();
        }
    }
