    public void shutdown(List<String> addresses) {
        println("Shutting down remote engines");
        for (String address : addresses) {
            try {
                if (engines.containsKey(address)) {
                    engines.get(address).stopTest(false);
                } else {
                    log.warn("Host not found in list of active engines: " + address);
                }

            } catch (RuntimeException e) {
                errln("Failed to shutdown test on " + address, e);
            }
        }
        println("Remote engines have been shut down");
    }
