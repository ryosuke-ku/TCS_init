    private void add(String value)
    {
        if (!acceptsArg())
        {
            throw new RuntimeException("Cannot add value, list full.");
        }

        // store value
        values.add(value);
    }
