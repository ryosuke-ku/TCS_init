    private void createAccountOfOriginType(String userName, OriginType originType) {
        MyContext myContext = MyContextHolder.get();
        String logMsg = "Creating account '" + userName + "' for '" + originType + "'";
        MyLog.v(this, logMsg);
        Origin origin = myContext.persistentOrigins().firstOfType(originType);
        MyAccount.Builder builder = MyAccount.Builder.newOrExistingFromAccountName(myContext,
                userName + AccountName.ORIGIN_SEPARATOR + origin.getName(), TriState.UNKNOWN);
        assertEquals(logMsg, origin.getId(), builder.getAccount().getOriginId());
        assertEquals(logMsg, userName + AccountName.ORIGIN_SEPARATOR + origin.getName(), builder.getAccount().getAccountName());
    }
