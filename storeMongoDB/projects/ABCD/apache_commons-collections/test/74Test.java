    public void testTransformedCollection_2() {
        final List<Object> list = new ArrayList<Object>();
        list.add("1");
        list.add("2");
        list.add("3");
        final Collection<Object> result = CollectionUtils.transformingCollection(list, TRANSFORM_TO_INTEGER);
        assertEquals(true, result.contains("1")); // untransformed
        assertEquals(true, result.contains("2")); // untransformed
        assertEquals(true, result.contains("3")); // untransformed
    }
