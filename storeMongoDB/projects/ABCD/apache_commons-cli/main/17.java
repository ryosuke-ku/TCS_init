    public String getValue(String defaultValue)
    {
        String value = getValue();

        return (value != null) ? value : defaultValue;
    }
