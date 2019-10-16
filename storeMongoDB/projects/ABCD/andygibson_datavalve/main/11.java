    public int fillCount() {
        int result = 0;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != null) {
                result++;
            }
        }
        return result;
    }
