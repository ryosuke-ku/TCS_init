    /* package */ boolean contentsExcluded(final TokenizedPath path) {
        return Stream.of(excludePatterns)
            .filter(p -> p.endsWith(SelectorUtils.DEEP_TREE_MATCH))
            .map(TokenizedPattern::withoutLastToken)
            .anyMatch(wlt -> wlt.matchPath(path, isCaseSensitive()));
    }
