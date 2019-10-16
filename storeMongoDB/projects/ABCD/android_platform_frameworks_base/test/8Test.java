    public void testParcel() throws Exception {
        VerificationParams expected = new VerificationParams(VERIFICATION_URI, ORIGINATING_URI,
                REFERRER, ORIGINATING_UID);

        Parcel parcel = Parcel.obtain();
        expected.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        VerificationParams actual = VerificationParams.CREATOR.createFromParcel(parcel);

        assertEquals(VERIFICATION_URI, actual.getVerificationURI());

        assertEquals(ORIGINATING_URI, actual.getOriginatingURI());

        assertEquals(REFERRER, actual.getReferrer());

        assertEquals(ORIGINATING_UID, actual.getOriginatingUid());
    }
