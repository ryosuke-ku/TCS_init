    public void addWord(String dictionaryName, final String wordToSave) {
        Validate.notNull(wordToSave);
        String word = wordToSave.toLowerCase();

        String prefix = null;
        //Add all the variants
        for (int i = 1; i < word.length(); i++) {
            prefix = word.substring(0, i);
            getDictionary(dictionaryName).add(prefix, 0);
            LOGGER.debug("Added prefix [{}]", prefix);
        }
        //Add the full word with End prefix to identify full word
        getDictionary(dictionaryName).add(word + END_TOKEN, 0);
        LOGGER.debug("Added word ** [{}]", word);
    }
