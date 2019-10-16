        public Builder withValues(ContentValues values) {
            if (mType != TYPE_INSERT && mType != TYPE_UPDATE && mType != TYPE_ASSERT) {
                throw new IllegalArgumentException(
                        "only inserts, updates, and asserts can have values");
            }
            if (mValues == null) {
                mValues = new ContentValues();
            }
            mValues.putAll(values);
            return this;
        }
