  static public void setUp() throws Exception {
    RpcHelper.DEBUG_SENDJSON = true;
    AsyncSingletonServer.killswitch.set(false);
    exec = Executors.newScheduledThreadPool(2);
    exec.submit(new Runnable() {
      public void run() {
        AsioVisitor topLevel = new ProtocolMethodDispatch();
        try {
          AsyncSingletonServer.SingleThreadSingletonServer.init(topLevel);
        } catch (Exception e) {
          fail();
        }
      }
    });
    nukeTestDbs();

    {
      CouchTx tx = new DbCreate().db(SOMEDB).to().fire().tx();
      assertNotNull(tx);
      assertTrue(tx.ok());
      assertNull(tx.getError());
    }

  }
