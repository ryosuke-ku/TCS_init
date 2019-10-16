  public void testPersistenceTimer2() throws Exception {
    final Environment env = KnowledgeBaseFactory.newEnvironment();
    env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
    env.set(EnvironmentName.TRANSACTION_MANAGER, TransactionManagerServices.getTransactionManager());

    final Properties properties = new Properties();
    properties.setProperty("drools.commandService", SingleSessionCommandService.class.getName());
    properties.setProperty("drools.processInstanceManagerFactory", JPAProcessInstanceManagerFactory.class.getName());
    properties.setProperty("drools.workItemManagerFactory", JPAWorkItemManagerFactory.class.getName());
    properties.setProperty("drools.processSignalManagerFactory", JPASignalManagerFactory.class.getName());
    properties.setProperty("drools.timerService", JpaJDKTimerService.class.getName());
    final SessionConfiguration config = new SessionConfiguration(properties);

    final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    final Collection<KnowledgePackage> kpkgs = getProcessTimer2();
    kbase.addKnowledgePackages(kpkgs);

    SingleSessionCommandService service = new SingleSessionCommandService(kbase, config, env);
    final int sessionId = service.getSessionId();
    final StartProcessCommand startProcessCommand = new StartProcessCommand();
    startProcessCommand.setProcessId("org.drools.test.TestProcess");
    ProcessInstance processInstance = service.execute(startProcessCommand);
    System.out.println("Started process instance " + processInstance.getId());

    Thread.sleep(2000);

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    final GetProcessInstanceCommand getProcessInstanceCommand = new GetProcessInstanceCommand();
    getProcessInstanceCommand.setProcessInstanceId(processInstance.getId());
    processInstance = service.execute(getProcessInstanceCommand);
    assertNull(processInstance);
  }
