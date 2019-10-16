    public void testGetCorpUser() {
        final Context c = mActor.getProviderContext();
        final MockUserManager um = mActor.mockUserManager;

        // No corp user.  Primary only.
        assertEquals(-1, UserUtils.getCorpUserId(c));

        // Primary + corp
        um.setUsers(MockUserManager.PRIMARY_USER, MockUserManager.CORP_USER);

        um.myUser = MockUserManager.PRIMARY_USER.id;
        assertEquals(MockUserManager.CORP_USER.id, UserUtils.getCorpUserId(c));

        um.myUser = MockUserManager.CORP_USER.id;
        assertEquals(-1, UserUtils.getCorpUserId(c));

        // Primary + secondary + corp
        um.setUsers(MockUserManager.PRIMARY_USER, MockUserManager.SECONDARY_USER,
                MockUserManager.CORP_USER);

        um.myUser = MockUserManager.PRIMARY_USER.id;
        assertEquals(MockUserManager.CORP_USER.id, UserUtils.getCorpUserId(c));

        um.myUser = MockUserManager.CORP_USER.id;
        assertEquals(-1, UserUtils.getCorpUserId(c));

        um.myUser = MockUserManager.SECONDARY_USER.id;
        assertEquals(-1, UserUtils.getCorpUserId(c));

        // Primary + secondary
        um.setUsers(MockUserManager.PRIMARY_USER, MockUserManager.SECONDARY_USER);

        um.myUser = MockUserManager.PRIMARY_USER.id;
        assertEquals(-1, UserUtils.getCorpUserId(c));

        um.myUser = MockUserManager.SECONDARY_USER.id;
        assertEquals(-1, UserUtils.getCorpUserId(c));
    }
