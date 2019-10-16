    public void testVerifierDeviceIdentity_Parcel_ReadNegative() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_MINVALUE);

        Parcel parcel = Parcel.obtain();
        parcel.writeLong(TEST_MINVALUE);
        parcel.setDataPosition(0);

        VerifierDeviceIdentity id2 = VerifierDeviceIdentity.CREATOR.createFromParcel(parcel);

        assertEquals("Parcel created should match expected value", id1, id2);
    }
