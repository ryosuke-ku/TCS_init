  public void compute(
      Vertex<IntWritable, IntArrayListWritable, NullWritable> vertex,
      Iterable<IntWritable> messages) throws IOException {
    if (getSuperstep() == 0) {
      // send list of this vertex's neighbors to all neighbors
      for (Edge<IntWritable, NullWritable> edge : vertex.getEdges()) {
        sendMessageToAllEdges(vertex, edge.getTargetVertexId());
      }
    } else {
      for (IntWritable message : messages) {
        final int current = (closeMap.get(message) == null) ?
          0 : closeMap.get(message) + 1;
        closeMap.put(message, current);
      }
      // make sure the result values are sorted and
      // packaged in an IntArrayListWritable for output
      Set<Pair> sortedResults = Sets.<Pair>newTreeSet();
      for (Map.Entry<IntWritable, Integer> entry : closeMap.entrySet()) {
        sortedResults.add(new Pair(entry.getKey(), entry.getValue()));
      }
      IntArrayListWritable
        outputList = new IntArrayListWritable();
      for (Pair pair : sortedResults) {
        if (pair.value > 0) {
          outputList.add(pair.key);
        } else {
          break;
        }
      }
      vertex.setValue(outputList);
    }
    vertex.voteToHalt();
  }
