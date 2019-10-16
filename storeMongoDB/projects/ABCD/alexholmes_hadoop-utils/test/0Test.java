    public void runEndScenario1() throws Exception {

        TextIOJobBuilder builder = new TextIOLocalJobBuilder(new Configuration(), TEST_ROOT_DIR)
                .addInput("foo bar")
                .addInput("clump")
                .addExpectedOutput("clump")
                .addExpectedOutput("foo bar")
                .writeInputs();

        run(new SortConfig(builder.getFs().getConf()).setStartKey(1).setEndKey(1), builder);
    }
