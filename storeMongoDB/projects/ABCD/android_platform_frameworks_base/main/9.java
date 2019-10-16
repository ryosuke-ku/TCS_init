        public ContentProviderOperation build() {
            if (mType == TYPE_UPDATE) {
                if ((mValues == null || mValues.size() == 0)
                        && (mValuesBackReferences == null || mValuesBackReferences.size() == 0)) {
                    throw new IllegalArgumentException("Empty values");
                }
            }
            if (mType == TYPE_ASSERT) {
                if ((mValues == null || mValues.size() == 0)
                        && (mValuesBackReferences == null || mValuesBackReferences.size() == 0)
                        && (mExpectedCount == null)) {
                    throw new IllegalArgumentException("Empty values");
                }
            }
            return new ContentProviderOperation(this);
        }
