    IdAndVersionStreamVerifierListener createStandardPrintStreamListener(Function<Long, Object> formatter) {
        return new PrintStreamOutputVersionStreamVerifierListener(System.err, formatter);
    }
