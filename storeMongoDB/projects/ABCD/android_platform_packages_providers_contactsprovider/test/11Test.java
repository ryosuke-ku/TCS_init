    public void testShouldPreferSimplifiedChinese() {
        assertFalse(LocaleSet.newForTest(Locale.ENGLISH)
                .shouldPreferSimplifiedChinese());
        assertFalse(LocaleSet.newForTest(Locale.TRADITIONAL_CHINESE)
                .shouldPreferSimplifiedChinese());

        assertTrue(LocaleSet.newForTest(Locale.SIMPLIFIED_CHINESE)
                .shouldPreferSimplifiedChinese());
        assertTrue(LocaleSet.newForTest(Locale.ENGLISH, Locale.KOREAN, Locale.SIMPLIFIED_CHINESE)
                .shouldPreferSimplifiedChinese());
        assertTrue(LocaleSet.newForTest(Locale.SIMPLIFIED_CHINESE, Locale.JAPANESE)
                .shouldPreferSimplifiedChinese());
        assertTrue(LocaleSet.newForTest(Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE)
                .shouldPreferSimplifiedChinese());

        // Simplified Chinese wins.
        assertFalse(LocaleSet.newForTest(Locale.JAPAN, Locale.SIMPLIFIED_CHINESE)
                .shouldPreferSimplifiedChinese());
        assertFalse(LocaleSet.newForTest(Locale.TRADITIONAL_CHINESE, Locale.SIMPLIFIED_CHINESE)
                .shouldPreferSimplifiedChinese());
    }
