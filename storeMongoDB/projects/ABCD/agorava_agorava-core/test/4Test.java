    public void shouldNotAllowStrangeProtocolNames() {
        Preconditions.checkValidUrl("$weird*://www.example.com", ERROR_MSG);
    }
