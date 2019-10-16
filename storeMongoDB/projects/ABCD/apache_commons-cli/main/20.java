    public String[] getValues()
    {
        return hasNoValues() ? null : values.toArray(new String[values.size()]);
    }
