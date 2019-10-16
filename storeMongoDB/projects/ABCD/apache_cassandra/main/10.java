    public static boolean isNameValid(String name)
    {
        return name != null && !name.isEmpty()
               && name.length() <= SchemaConstants.NAME_LENGTH && PATTERN_WORD_CHARS.matcher(name).matches();
    }
