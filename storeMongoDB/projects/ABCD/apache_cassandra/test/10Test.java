    public void testIsNameValidPositive()
    {
         assertTrue(CFMetaData.isNameValid("abcdefghijklmnopqrstuvwxyz"));
         assertTrue(CFMetaData.isNameValid("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
         assertTrue(CFMetaData.isNameValid("_01234567890"));
    }
