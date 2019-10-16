    public ImmutableList<IMarkerResolution> getQuickFixes(final PMDMarker marker, final JavaQuickFixContext context) {
        final ImmutableList.Builder<IMarkerResolution> quickFixes = ImmutableList.builder();
        if (context.getCompilerCompliance().compareTo(JAVA_8) < 0) {
            for (final Class<? extends IMarkerResolution> quickFixClass : QUICK_FIXES.get(marker.getRuleId())) {
                quickFixes.add(createInstanceOf(quickFixClass, marker));
            }
        }
        if (context.getCompilerCompliance().compareTo(JAVA_5) >= 0) {
            quickFixes.add(new SuppressWarningsQuickFix(marker));
        }
        return quickFixes.build();
    }
