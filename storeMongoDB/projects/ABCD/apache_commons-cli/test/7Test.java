    private static void checkOption(Option option, String opt, String description, String longOpt, int numArgs,
                                    String argName,  boolean required, boolean optionalArg,
                                    char valueSeparator, Class<?> cls)
    {
        assertEquals(opt, option.getOpt());
        assertEquals(description, option.getDescription());
        assertEquals(longOpt, option.getLongOpt());
        assertEquals(numArgs, option.getArgs());
        assertEquals(argName, option.getArgName());
        assertEquals(required, option.isRequired());

        assertEquals(optionalArg, option.hasOptionalArg());
        assertEquals(valueSeparator, option.getValueSeparator());
        assertEquals(cls,  option.getType());
    }
