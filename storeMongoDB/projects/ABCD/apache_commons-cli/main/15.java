    void addValueForProcessing(String value)
    {
        if (numberOfArgs == UNINITIALIZED)
        {
            throw new RuntimeException("NO_ARGS_ALLOWED");
        }
        processValue(value);
    }
