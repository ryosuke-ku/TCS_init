    public List<String> findWords(String dictionaryName, final String searchKeyword, final int max) {
        Validate.isTrue(max <= MAX_COUNT);
        String prefix = searchKeyword.toLowerCase();

        List<String> results = new ArrayList<String>();

        Long start = getDictionary(dictionaryName).rank(prefix);

        //if start is null then check if this is a complete word and try to find it
        if (start == null) {
            start = getDictionary(dictionaryName).rank(prefix + END_TOKEN);
        }

        LOGGER.info("Rank of prefix [{}] was [{}]", prefix, start);

        if (start != null) {
            while (true) {
                Set<String> rangeRecs = getDictionary(dictionaryName).range(start, start + MAX_TRANS_UNIT - 1);

                start += MAX_TRANS_UNIT;

                if (CollectionUtils.isNotEmpty(rangeRecs)) {
                    for (String entry : rangeRecs) {

                        LOGGER.debug("Processing entry [{}]", entry);
                        //If the entry is shorter than prefix then a new sequence has started
                        //Also if entry doesn't have the same prefix
                        if (entry.length() < prefix.length()
                                || entry.substring(0, prefix.length()).equals(prefix) == false
                                || results.size() >= max) {
                            LOGGER.info("Returning at prefix [{}]; end condition satisfied; ", entry);
                            return results;

                        } else if (entry.endsWith(END_TOKEN)) {
                            //Remove the end token before returning
                            String word = entry.substring(0, entry.length() - END_TOKEN.length());
                            results.add(word);
                            LOGGER.debug("Found word [{}]", word);
                        } else {
                            LOGGER.debug("Prefix found [{}]", entry);
                        }
                    }
                } else {
                    LOGGER.info("Returning as no more to read");
                    return results;
                }
            }
        }
        return results;
    }
