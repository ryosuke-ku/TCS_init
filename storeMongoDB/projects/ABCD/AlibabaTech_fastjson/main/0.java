    public String addSymbol(String buffer, int offset, int len, int hash) {
        // int bucket = indexFor(hash, tableSize);
        final int bucket = hash & indexMask;

        String sym = symbols[bucket];

        boolean match = true;

        if (sym != null) {
            if (sym.length() == len) {
                char[] characters = symbols_char[bucket];

                for (int i = 0; i < len; i++) {
                    if (buffer.charAt(offset + i) != characters[i]) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    return sym;
                }
            } else {
                match = false;
            }
        }

        {
            int entryIndex = 0;
            for (Entry entry = buckets[bucket]; entry != null; entry = entry.next) {
                char[] characters = entry.characters;
                if (len == characters.length && hash == entry.hashCode) {
                    boolean eq = true;
                    for (int i = 0; i < len; i++) {
                        if (buffer.charAt(offset + i) != characters[i]) {
                            eq = false;
                            break;
                        }
                    }

                    if (!eq) {
                        entryIndex++;
                        continue;
                    }
                    return entry.symbol;
                }
            }
            if (entryIndex >= MAX_BUCKET_LENTH) {
                return buffer.substring(offset, offset + len);
//                return new String(buffer, offset, len);
            }
        }

        if (size >= MAX_SIZE) {
//            return new String(buffer, offset, len);
            return buffer.substring(offset, offset + len);
        }

        Entry entry = new Entry(buffer, offset, len, hash, buckets[bucket]);
