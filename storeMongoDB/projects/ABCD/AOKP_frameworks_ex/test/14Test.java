    public void testParseOptions() throws Exception {
         OperationScheduler.Options options = new OperationScheduler.Options();
         assertEquals(
                 "OperationScheduler.Options[backoff=0.0+5.0 max=86400.0 min=0.0 period=3600.0]",
                 OperationScheduler.parseOptions("3600", options).toString());

         assertEquals(
                 "OperationScheduler.Options[backoff=0.0+2.5 max=86400.0 min=0.0 period=3700.0]",
                 OperationScheduler.parseOptions("backoff=+2.5 3700", options).toString());

         assertEquals(
                 "OperationScheduler.Options[backoff=10.0+2.5 max=12345.6 min=7.0 period=3800.0]",
                 OperationScheduler.parseOptions("max=12345.6 min=7 backoff=10 period=3800",
                         options).toString());

         assertEquals(
                "OperationScheduler.Options[backoff=10.0+2.5 max=12345.6 min=7.0 period=3800.0]",
                 OperationScheduler.parseOptions("", options).toString());
    }
