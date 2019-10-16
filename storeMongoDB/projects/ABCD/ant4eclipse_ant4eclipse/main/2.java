  public List<T> calculateOrder() {

    // setup a matrix that contains a true value iff there is a the first index donates a parent of the second value
    boolean[][] matrix = new boolean[this._vertices.size()][this._vertices.size()];

    // fill the diagonale
    for (boolean[] element : matrix) {
      Arrays.fill(element, false);
    }

    // set each value to true iff there is a relationship form first to second...
    for (int i = 0; i < this._edges.size(); i++) {
      Edge<T> edge = this._edges.get(i);
      int fromidx = this._vertices.indexOf(edge.getParent());
      int toidx = this._vertices.indexOf(edge.getChild());

      if ((fromidx == -1) || (toidx == -1)) {
        // one of the edge's vertices has not been
        // added to this graph (f.e. if it's a dependency
        // on the outside of a specific context)
        continue;
      }

      matrix[fromidx][toidx] = true;
    }

    List<T> list = new LinkedList<T>();

    // iterates across the matrix as long as we didn't found
    // a cycle and there are still edges to be processed
    while (reduce(list, matrix)) {
      // nothing to do...
    }

    return list;
  }
