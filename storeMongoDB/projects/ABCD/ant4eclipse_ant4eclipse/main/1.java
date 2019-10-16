  public void addEdge(T parent, T child) {
    Assure.notNull("parent", parent);
    Assure.notNull("child", child);
    addVertex(parent);
    addVertex(child);
    this._edges.add(new Edge<T>(parent, child));
  }
