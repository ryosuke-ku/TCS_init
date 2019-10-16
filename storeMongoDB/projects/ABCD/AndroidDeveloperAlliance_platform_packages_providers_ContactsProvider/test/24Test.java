    public void testCountPhoneNumberDigits() {
        assertEquals(10, ContactsProvider2.countPhoneNumberDigits("86 (0) 5-55-12-34"));
        assertEquals(10, ContactsProvider2.countPhoneNumberDigits("860 555-1234"));
        assertEquals(3, ContactsProvider2.countPhoneNumberDigits("860"));
        assertEquals(10, ContactsProvider2.countPhoneNumberDigits("8605551234"));
        assertEquals(6, ContactsProvider2.countPhoneNumberDigits("860555"));
        assertEquals(6, ContactsProvider2.countPhoneNumberDigits("860 555"));
        assertEquals(6, ContactsProvider2.countPhoneNumberDigits("860-555"));
        assertEquals(12, ContactsProvider2.countPhoneNumberDigits("+441234098765"));
        assertEquals(0, ContactsProvider2.countPhoneNumberDigits("44+1234098765"));
        assertEquals(0, ContactsProvider2.countPhoneNumberDigits("+441234098foo"));
    }
