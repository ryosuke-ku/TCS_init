    public String build() {
        if (mWhereClauses.size() == 0) {
            return null;
        }
        return DbQueryUtils.concatenateClauses(mWhereClauses.toArray(EMPTY_STRING_ARRAY));
    }
