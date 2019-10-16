  public void visitNonDef(EdgeVisitor visitor) {
    for (int lead = 0; lead < leads.length; lead++) {
      final int fromIncl = leads[lead][0];
      final int uptoExcl = leads[lead][1];
      for (int pos = fromIncl; pos < uptoExcl; pos++) {
        final int trail = trails.get(pos, 0);
        final double elem = data.get(pos, .0);
        //  TODO this means that we have only one visit for symmetric EdgeDataSparse, HOUSTON?
        visitor.visit(lead, trail, elem);
      }
    }
  }
