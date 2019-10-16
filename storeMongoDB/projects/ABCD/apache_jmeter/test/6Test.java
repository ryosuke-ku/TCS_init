    public void testSuccess() throws Exception {
        createJmeterEnv();
        JMeterUtils.setProperty(DistributedRunner.RETRIES_NUMBER, "1");
        JMeterUtils.setProperty(DistributedRunner.CONTINUE_ON_FAIL, "false");
        DistributedRunnerEmul obj = new DistributedRunnerEmul();
        obj.engines.add(new EmulatorEngine());
        obj.engines.add(new EmulatorEngine());
        List<String> hosts = Arrays.asList("test1", "test2");
        obj.init(hosts, new HashTree());
        obj.start();
        obj.shutdown(hosts);
        obj.stop(hosts);
        obj.exit(hosts);
    }
