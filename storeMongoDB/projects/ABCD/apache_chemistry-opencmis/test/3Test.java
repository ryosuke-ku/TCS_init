    public void testHelpers() {
        assertTrue(CollectionsHelper.isNullOrEmpty((List<String>) null));
        assertTrue(CollectionsHelper.isNullOrEmpty((Map<String, String>) null));
        assertFalse(CollectionsHelper.isNotEmpty((List<String>) null));
        assertFalse(CollectionsHelper.isNotEmpty((Map<String, String>) null));

        assertTrue(CollectionsHelper.isNullOrEmpty(Collections.emptyList()));
        assertTrue(CollectionsHelper.isNullOrEmpty(Collections.emptyMap()));
        assertFalse(CollectionsHelper.isNotEmpty(Collections.emptyList()));
        assertFalse(CollectionsHelper.isNotEmpty(Collections.emptyMap()));

        assertFalse(CollectionsHelper.isNullOrEmpty(Collections.singletonList("value")));
        assertFalse(CollectionsHelper.isNullOrEmpty(Collections.singletonMap("key", "value")));
        assertTrue(CollectionsHelper.isNotEmpty(Collections.singletonList("value")));
        assertTrue(CollectionsHelper.isNotEmpty(Collections.singletonMap("key", "value")));
    }
