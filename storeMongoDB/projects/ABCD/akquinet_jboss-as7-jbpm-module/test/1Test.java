  public void testPersistenceWorkItems() throws Exception {
    final Environment env = KnowledgeBaseFactory.newEnvironment();
    env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);
    env.set(EnvironmentName.TRANSACTION_MANAGER, TransactionManagerServices.getTransactionManager());

    final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    final Collection<KnowledgePackage> kpkgs = getProcessWorkItems();
    kbase.addKnowledgePackages(kpkgs);

    final Properties properties = new Properties();
    properties.setProperty("drools.commandService", SingleSessionCommandService.class.getName());
    properties.setProperty("drools.processInstanceManagerFactory", JPAProcessInstanceManagerFactory.class.getName());
    properties.setProperty("drools.workItemManagerFactory", JPAWorkItemManagerFactory.class.getName());
    properties.setProperty("drools.processSignalManagerFactory", JPASignalManagerFactory.class.getName());
    properties.setProperty("drools.timerService", JpaJDKTimerService.class.getName());
    final SessionConfiguration config = new SessionConfiguration(properties);

    SingleSessionCommandService service = new SingleSessionCommandService(kbase, config, env);
    final int sessionId = service.getSessionId();

    final StartProcessCommand startProcessCommand = new StartProcessCommand();
    startProcessCommand.setProcessId("org.drools.test.TestProcess");
    ProcessInstance processInstance = service.execute(startProcessCommand);
    System.out.println("Started process instance " + processInstance.getId());

    final TestWorkItemHandler handler = TestWorkItemHandler.getInstance();
    WorkItem workItem = handler.getWorkItem();
    assertNotNull(workItem);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    GetProcessInstanceCommand getProcessInstanceCommand = new GetProcessInstanceCommand();
    getProcessInstanceCommand.setProcessInstanceId(processInstance.getId());
    processInstance = service.execute(getProcessInstanceCommand);
    assertNotNull(processInstance);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    CompleteWorkItemCommand completeWorkItemCommand = new CompleteWorkItemCommand();
    completeWorkItemCommand.setWorkItemId(workItem.getId());
    service.execute(completeWorkItemCommand);

    workItem = handler.getWorkItem();
    assertNotNull(workItem);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    getProcessInstanceCommand = new GetProcessInstanceCommand();
    getProcessInstanceCommand.setProcessInstanceId(processInstance.getId());
    processInstance = service.execute(getProcessInstanceCommand);
    assertNotNull(processInstance);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    completeWorkItemCommand = new CompleteWorkItemCommand();
    completeWorkItemCommand.setWorkItemId(workItem.getId());
    service.execute(completeWorkItemCommand);

    workItem = handler.getWorkItem();
    assertNotNull(workItem);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    getProcessInstanceCommand = new GetProcessInstanceCommand();
    getProcessInstanceCommand.setProcessInstanceId(processInstance.getId());
    processInstance = service.execute(getProcessInstanceCommand);
    assertNotNull(processInstance);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    completeWorkItemCommand = new CompleteWorkItemCommand();
    completeWorkItemCommand.setWorkItemId(workItem.getId());
    service.execute(completeWorkItemCommand);

    workItem = handler.getWorkItem();
    assertNull(workItem);
    service.dispose();

    service = new SingleSessionCommandService(sessionId, kbase, config, env);
    getProcessInstanceCommand = new GetProcessInstanceCommand();
    getProcessInstanceCommand.setProcessInstanceId(processInstance.getId());
    processInstance = service.execute(getProcessInstanceCommand);
    assertNull(processInstance);
    service.dispose();
  }
