  public void cyclicDependencyGraph() {
    String o1 = "o1";
    String o2 = "o2";
    String o3 = "o3";

    DependencyGraph<String> graph = new DependencyGraph<String>();

    graph.addVertex(o1);
    graph.addVertex(o2);
    graph.addVertex(o3);

    graph.addEdge(o1, o2);
    graph.addEdge(o2, o3);
    graph.addEdge(o3, o1);

    try {
      graph.calculateOrder();
      Assert.fail();
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.CYCLIC_DEPENDENCIES_EXCEPTION, ex.getExceptionCode());
    }
  }
