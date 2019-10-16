    public void testGetQuickFixes() {
        final ImmutableList<IMarkerResolution> quickFixes = new JavaQuickFixGenerator().getQuickFixes(marker, context);

        final Class<?>[] actualQuickFixClasses = new Class<?>[quickFixes.size()];
        for (int i = 0; i < quickFixes.size(); i++) {
            actualQuickFixClasses[i] = quickFixes.get(i).getClass();
        }

        assertArrayEquals("Quick fixes for rule " + ruleId + " and java version " + javaVersion, expectedQuickFixClasses,
                actualQuickFixClasses);
    }
