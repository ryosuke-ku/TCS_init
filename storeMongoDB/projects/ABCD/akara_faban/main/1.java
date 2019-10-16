    public Set<String> search(String[] tags) {

        HashSet<String> finalAnswer = new HashSet<String>();
        if (tags != null) {
            int count = 0;
            rlock.lock();
            try {
                for (String tag : tags) {
                    // In many instances, the separator is URL encoded from '/'
                    // to "+", so we have to change them back.
                    tag = tag.replace("+", "/");
                    HashSet<String> answer = new HashSet<String>();
                    Entry entry = findEntry(tag);
                    if (entry != null) {

                        // Flatten the entry hierarchy into the ArrayList entries.
                        ArrayList<Entry> entries = new ArrayList<Entry>();
                        entries.addAll(entry.subtags.values());

                        // Keep flattening down the hierarchy
                        // entries.size keeps increasing as we go down
                        // the hierarchy. That's why we need to keep
                        // evaluating entries.size() and cannot use an iterator.
                        for (int i = 0; i < entries.size(); i++) {
                            Entry subEntry = entries.get(i);
                            entries.addAll(subEntry.subtags.values());
                        }

                        // Adds run ids from the flattened entries to the answer.
                        answer.addAll(entry.runIds);
                        for (Entry subEntry : entries) {
                            answer.addAll(subEntry.runIds);
                        }
                        if (count == 0) {
                            finalAnswer = answer;
                        }
                    }
                    finalAnswer.retainAll(answer);
                    answer = null;
                    count++;
                }
            } finally {
                rlock.unlock();
            }
        } 
        return finalAnswer;
    }
