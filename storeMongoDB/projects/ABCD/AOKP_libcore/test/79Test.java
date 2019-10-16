                    public void run() {
                        try {
                            Thread.sleep(1*1000);
                            NativeCrypto.SSL_interrupt(s);
                        } catch (Exception e) {
                        }
                    }
