        public int getColumnIndex(String columnName) {
            for (int i=0; i<projection.length; ++i) {
                if (columnName.equals(projection[i])) {
                    return i;
                }
            }
            return -1;
        }
