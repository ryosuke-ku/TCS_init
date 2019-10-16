            public Socket createSocket(final String host, final int port)
                throws IOException, UnknownHostException {
                LOG.info("Creating socket");
                return createSocket(InetAddress.getByName(host), port);
            }
