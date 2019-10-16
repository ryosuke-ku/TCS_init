    public void testOverride() {
        CommandFactoryFactory factoryFactory = new CommandFactoryFactory();
        factoryFactory.addCommand("stor", new NOOP());

        CommandFactory factory = factoryFactory.createCommandFactory();

        Command command = factory.getCommand("Stor");

        assertTrue(command instanceof NOOP);
    }
