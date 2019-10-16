    public static SmsRecycler getSmsRecycler() {
        if (sSmsRecycler == null) {
            sSmsRecycler = new SmsRecycler();
        }
        return sSmsRecycler;
    }
