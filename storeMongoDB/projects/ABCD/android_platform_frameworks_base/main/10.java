        public Builder withValueBackReference(String key, int previousResult) {
            if (mType != TYPE_INSERT && mType != TYPE_UPDATE && mType != TYPE_ASSERT) {
                throw new IllegalArgumentException(
                        "only inserts, updates, and asserts can have value back-references");
            }
            if (mValuesBackReferences == null) {
                mValuesBackReferences = new ContentValues();
            }
            mValuesBackReferences.put(key, previousResult);
            return this;
        }
