        public Builder withSelectionBackReference(int selectionArgIndex, int previousResult) {
            if (mType != TYPE_UPDATE && mType != TYPE_DELETE && mType != TYPE_ASSERT) {
                throw new IllegalArgumentException("only updates, deletes, and asserts "
                        + "can have selection back-references");
            }
            if (mSelectionArgsBackReferences == null) {
                mSelectionArgsBackReferences = new HashMap<Integer, Integer>();
            }
            mSelectionArgsBackReferences.put(selectionArgIndex, previousResult);
            return this;
        }
