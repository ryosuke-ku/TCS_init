    public void exit(List<String> addresses) {
        println("Exiting remote engines");
        for (String address : addresses) {
            try {
                if (engines.containsKey(address)) {
                    engines.get(address).exit();
                } else {
                    log.warn("Host not found in list of active engines: " + address);
                }
            } catch (RuntimeException e) {
                errln("Failed to exit on " + address, e);
            }
        }
        println("Remote engines have been exited");
    }
