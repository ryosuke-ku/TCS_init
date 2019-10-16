    public void testFindByCompleteWords() throws Exception {

        List<String> results = redisWordCompletionDictionary.findWords("tmp", "foo");
        assertEquals(2, results.size());
        assertTrue(results.contains("foo"));
        assertTrue(results.contains("foobar"));

        results = redisWordCompletionDictionary.findWords("tmp", "foobar");
        assertEquals(1, results.size());
        assertTrue(results.contains("foobar"));
    }
