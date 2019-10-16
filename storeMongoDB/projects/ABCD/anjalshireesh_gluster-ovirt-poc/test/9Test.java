    public void testGetTagIdsAndChildrenIdsByRegExpNoTagMatches() {
        String result = tagsDirector.GetTagIdsAndChildrenIdsByRegExp("tag*");
        assertEquals(StringUtils.EMPTY, result);
    }
