    public static XMPPConnection persistentXmppConnection(
        final XmppCredentials credentials, final int attempts,
        final String host, final int port, final String serviceName,
        final XmppP2PClient clientListener)
            throws IOException, CredentialException {
        final String key = credentials.getKey();
        if (xmppConnections.containsKey(key)) {
            final XMPPConnection conn = xmppConnections.get(key);
            if (isEstablished(conn)) {
                LOG.info("Returning existing xmpp connection");
                return conn;
            } else {
                LOG.info("Removing stale connection");
                xmppConnections.remove(key);
            }
        }
        XMPPException exc = null;
        final XmppConnectionRetyStrategy strategy = XmppConfig.newRetyStrategy();
        while (strategy.retry()) {
            try {
                LOG.debug("Attempting XMPP connection...");
                final XMPPConnection conn =
                    singleXmppConnection(credentials, host, port,
                        serviceName, clientListener);

                LOG.debug("Created offerer");
                xmppConnections.put(key, conn);
                return conn;
            } catch (final XMPPException e) {
                LOG.error("Error creating XMPP connection", e);
                exc = e;
            }

            // Gradual backoff.
            strategy.sleep();
        }
        if (exc != null) {
            throw new IOException("Could not log in!!", exc);
        }
        else {
            throw new IOException("Could not log in?");
        }
    }
