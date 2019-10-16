    public void add(String runId, String[] tags) {
        wlock.lock();
        try {
            HashSet<Entry> entrySet = runEntries.get(runId);
            if(entrySet == null)
                entrySet = new HashSet<Entry>();
            HashSet<Entry> newEntrySet = new HashSet<Entry>();
            for (String tag : tags) {
                tag = tag.toLowerCase();
                String[] tagsArray = getTagsArray(tag);
                Entry entry = null;
                int count = 0;
                for(String subtag : tagsArray){
                    if (count == 0) {
                        entry = findEntry(subtag);
                        if (entry == null) {
                            entry = new Entry();
                            entry.fullTagName = subtag;
                            tagEntries.put(subtag, entry);
                        }
                    }else{
                        Entry subEntry = findEntry(subtag);
                        if (subEntry == null) {
                            subEntry  = new Entry();
                            subEntry.fullTagName = subtag;
                            tagEntries.put(subtag, subEntry);
                            entry.subtags.put(subtag, subEntry);
                        }
                    }
                    entry = tagEntries.get(subtag);
                    count++;
                }
                entry.runIds.add(runId);
                tagEntries.put(tag, entry);
                newEntrySet.add(entry);
            }

            HashSet<Entry> removeEntrySet = new HashSet<Entry>(entrySet);
            removeEntrySet.removeAll(newEntrySet);

            for (Entry entry : removeEntrySet) {
                entry.runIds.remove(runId);
                if (entry.runIds.size() == 0 && entry.subtags.size() == 0) {
                    //removeEntry(entry);
                    entry.subtags.remove(entry);
                }
            }
            entrySet.removeAll(removeEntrySet);
            entrySet.addAll(newEntrySet);
            runEntries.put(runId, entrySet);
        } finally {
            wlock.unlock();
        }
    }
