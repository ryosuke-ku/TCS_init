    public boolean shouldPreferSimplifiedChinese() {
        if (isLocaleSimplifiedChinese(getPrimaryLocale())) {
            return true;
        }
        for (int i = 0; i < mLocaleList.size(); i++) {
            final Locale l = mLocaleList.get(i);
            if (isLocaleSimplifiedChinese(l)) {
                return true;
            }
            if (isLanguageJapanese(l)) {
                return false;
            }
            if (isLocaleTraditionalChinese(l)) { // Traditional chinese wins here.
                return false;
            }
        }
        return false;
    }
