    IdAndVersionStreamVerifierListener createCoincidentPrintStreamListener(Function<Long, Object> formatter) {
        return new CoincidentFilteredStreamVerifierListener(new PrintStreamOutputVersionStreamVerifierListener(System.err, formatter));
    }
