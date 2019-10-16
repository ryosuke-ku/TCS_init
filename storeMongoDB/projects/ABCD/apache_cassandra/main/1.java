    public static Duration from(String input)
    {
        boolean isNegative = input.startsWith("-");
        String source = isNegative ? input.substring(1) : input;

        if (source.startsWith("P"))
        {
            if (source.endsWith("W"))
                return parseIso8601WeekFormat(isNegative, source);

            if (source.contains("-"))
                return parseIso8601AlternativeFormat(isNegative, source);

            return parseIso8601Format(isNegative, source);
        }
        return parseStandardFormat(isNegative, source);
    }
