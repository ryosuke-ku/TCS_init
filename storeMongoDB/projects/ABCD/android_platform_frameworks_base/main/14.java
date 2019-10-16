        public Builder withSelection(String selection, String[] selectionArgs) {
            if (mType != TYPE_UPDATE && mType != TYPE_DELETE && mType != TYPE_ASSERT) {
                throw new IllegalArgumentException(
                        "only updates, deletes, and asserts can have selections");
            }
            mSelection = selection;
            if (selectionArgs == null) {
                mSelectionArgs = null;
            } else {
                mSelectionArgs = new String[selectionArgs.length];
                System.arraycopy(selectionArgs, 0, mSelectionArgs, 0, selectionArgs.length);
            }
            return this;
        }
