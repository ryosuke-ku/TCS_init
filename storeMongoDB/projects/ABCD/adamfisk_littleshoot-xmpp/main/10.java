    public static RosterPacket extendedRoster(final XMPPConnection conn) {
        LOG.debug("Requesting extended roster");
        final String query =
            "<query xmlns:gr='google:roster' gr:ext='2' xmlns='jabber:iq:roster'/>";
        return (RosterPacket) getGTalkProperty(conn, query);
    }
