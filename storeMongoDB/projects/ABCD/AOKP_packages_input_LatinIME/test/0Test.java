    public void testIsDistractorToWordsInDictionaries() {
        final PrevWordsInfo EMPTY_PREV_WORDS_INFO = PrevWordsInfo.EMPTY_PREV_WORDS_INFO;

        final Locale localeEnUs = new Locale("en", "US");
        String typedWord;

        typedWord = "Bill";
        // For this test case, we consider "Bill" is a distracter to "bill".
        assertTrue(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

        typedWord = "nOt";
        // For this test case, we consider "nOt" is a distracter to "not".
        assertTrue(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

        typedWord = "youre";
        // For this test case, we consider "youre" is a distracter to "you're".
        assertTrue(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

        typedWord = "Banana";
        // For this test case, we consider "Banana" is a distracter to "banana".
        assertTrue(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

        typedWord = "orange";
        // For this test case, we consider "orange" is not a distracter to any word in dictionaries.
        assertFalse(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

        typedWord = "Orange";
        // For this test case, we consider "Orange" is a distracter to "orange".
        assertTrue(mDistracterFilter.isDistracterToWordsInDictionaries(
                EMPTY_PREV_WORDS_INFO, typedWord, localeEnUs));

