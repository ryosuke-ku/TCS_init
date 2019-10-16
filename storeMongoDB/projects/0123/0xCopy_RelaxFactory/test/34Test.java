      public void run() {
        AsioVisitor topLevel = new ProtocolMethodDispatch();
        try {
          AsyncSingletonServer.SingleThreadSingletonServer.init(topLevel);
        } catch (Exception e) {
          fail();
        }
      }
