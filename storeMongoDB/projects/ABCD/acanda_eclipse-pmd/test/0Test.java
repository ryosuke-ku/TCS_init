    public void hasQuickFixes() {
        final boolean hasQuickFixes = new JavaQuickFixGenerator().hasQuickFixes(marker, context);

        assertEquals("hasQuickFixes should return whether the generator has quick fixes for the rule " + ruleId
                + " and java version: " + javaVersion, expectedQuickFixClasses.length > 0, hasQuickFixes);
    }
