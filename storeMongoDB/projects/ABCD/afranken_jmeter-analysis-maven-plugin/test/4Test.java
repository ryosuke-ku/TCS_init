    public void testNoSamples() {

        Samples samples = createWithSamples(new int[] {});        
        Quantile quantiles = samples.getQuantiles(10);
        for (int i = 1; i <= 10; i++) {
            assertQuantile(quantiles, i, 0);
        }
    }
