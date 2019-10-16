    public void testBuilderMethods()
    {
        char defaultSeparator = (char) 0;

        checkOption(Option.builder("a").desc("desc").build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").longOpt("aaa").build(),
            "a", "desc", "aaa", Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").hasArg(true).build(),
            "a", "desc", null, 1, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").hasArg(false).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").hasArg(true).build(),
            "a", "desc", null, 1, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").numberOfArgs(3).build(),
            "a", "desc", null, 3, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").required(true).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, true, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").required(false).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);

        checkOption(Option.builder("a").desc("desc").argName("arg1").build(),
            "a", "desc", null, Option.UNINITIALIZED, "arg1", false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").optionalArg(false).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").optionalArg(true).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, true, defaultSeparator, String.class);
        checkOption(Option.builder("a").desc("desc").valueSeparator(':').build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, ':', String.class);
        checkOption(Option.builder("a").desc("desc").type(Integer.class).build(),
            "a", "desc", null, Option.UNINITIALIZED, null, false, false, defaultSeparator, Integer.class);
    }
