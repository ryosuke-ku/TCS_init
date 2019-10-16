    public void testVerifierDeviceIdentity_Parcel_Read_Pass() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_1);

        Parcel parcel = Parcel.obtain();
        id1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        VerifierDeviceIdentity id2 = VerifierDeviceIdentity.CREATOR.createFromParcel(parcel);

        assertEquals("Original identity and parceled identity should be the same", id1, id2);
    }
