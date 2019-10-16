    public boolean hasQuickFixes(final PMDMarker marker, final JavaQuickFixContext context) {
        if (context.getCompilerCompliance().compareTo(JAVA_5) >= 0) {
            // The SuppressWarningsQuickFix is always available when the compiler compliance is set to Java 5+.
            return true;
        }
        return QUICK_FIXES.containsKey(marker.getRuleId());
    }
