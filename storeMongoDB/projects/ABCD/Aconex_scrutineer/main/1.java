    public IdAndVersion next() {
        try {
            IdAndVersion result = currentValue;
            currentValue = idAndVersionDataReader.readNext();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
