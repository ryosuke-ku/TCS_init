    public void stop() {
        List<String> addresses = new LinkedList<>();
        addresses.addAll(engines.keySet());
        stop(addresses);
    }
