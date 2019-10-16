    public void testCheckPropertyCachingOnGetElement() {
        doReturn(AbstractSynonymsSaver.SKIPPED_PROPERTY).when(saver).createProperty(eq(TestNodeType.TEST_TYPE_FOR_SAVER),
                any(String.class), eq(false));

        saver.getElementProperties(TestNodeType.TEST_TYPE_FOR_SAVER, getData(5), false);
        saver.getElementProperties(TestNodeType.TEST_TYPE_FOR_SAVER, getData(5), false);

        verify(saver, times(5)).createProperty(eq(TestNodeType.TEST_TYPE_FOR_SAVER), any(String.class), eq(false));
    }
