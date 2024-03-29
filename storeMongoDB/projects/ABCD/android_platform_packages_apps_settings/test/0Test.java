    public void testFilter() {
        // Define the variables
        CharSequence source;
        SpannableStringBuilder dest;
        // Constructor to create a LengthFilter
        InputFilter lengthFilter = new Utf8ByteLengthFilter(10);
        InputFilter[] filters = {lengthFilter};

        // filter() implicitly invoked. If the total length > filter length, the filter will
        // cut off the source CharSequence from beginning to fit the filter length.
        source = "abc";
        dest = new SpannableStringBuilder("abcdefgh");
        dest.setFilters(filters);

        dest.insert(1, source);
        String expectedString1 = "aabbcdefgh";
        assertEquals(expectedString1, dest.toString());

        dest.replace(5, 8, source);
        String expectedString2 = "aabbcabcgh";
        assertEquals(expectedString2, dest.toString());

        dest.insert(2, source);
        assertEquals(expectedString2, dest.toString());

        dest.delete(1, 3);
        String expectedString3 = "abcabcgh";
        assertEquals(expectedString3, dest.toString());

        dest.append("12345");
        String expectedString4 = "abcabcgh12";
        assertEquals(expectedString4, dest.toString());

        source = "\u60a8\u597d";  // 2 Chinese chars == 6 bytes in UTF-8
        dest.replace(8, 10, source);
        assertEquals(expectedString3, dest.toString());

        dest.replace(0, 1, source);
        String expectedString5 = "\u60a8bcabcgh";
        assertEquals(expectedString5, dest.toString());

        dest.replace(0, 4, source);
        String expectedString6 = "\u60a8\u597dbcgh";
        assertEquals(expectedString6, dest.toString());

        source = "\u00a3\u00a5";  // 2 Latin-1 chars == 4 bytes in UTF-8
        dest.delete(2, 6);
        dest.insert(0, source);
        String expectedString7 = "\u00a3\u00a5\u60a8\u597d";
        assertEquals(expectedString7, dest.toString());

        dest.replace(2, 3, source);
        String expectedString8 = "\u00a3\u00a5\u00a3\u597d";
        assertEquals(expectedString8, dest.toString());

        dest.replace(3, 4, source);
        String expectedString9 = "\u00a3\u00a5\u00a3\u00a3\u00a5";
        assertEquals(expectedString9, dest.toString());

        // filter() explicitly invoked
        dest = new SpannableStringBuilder("abcdefgh");
        CharSequence beforeFilterSource = "TestLengthFilter";
        String expectedAfterFilter = "TestLength";
        CharSequence actualAfterFilter = lengthFilter.filter(beforeFilterSource, 0,
                beforeFilterSource.length(), dest, 0, dest.length());
        assertEquals(expectedAfterFilter, actualAfterFilter);
    }
