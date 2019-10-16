    public static String maybeQuote(String text)
    {
        if (UNQUOTED_IDENTIFIER.matcher(text).matches() && !ReservedKeywords.isReserved(text))
            return text;
        return '"' + PATTERN_DOUBLE_QUOTE.matcher(text).replaceAll(ESCAPED_DOUBLE_QUOTE) + '"';
    }
