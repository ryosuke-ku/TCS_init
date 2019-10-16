  private static void checkTuple(EdgeData.ElemIterator ndi, final int from, final int into, final double value) {
    assertTrue(ndi.hasNext());
    final EdgeData.IteratorTuple tuple = ndi.next();
    assertEquals(from, tuple.from());
    assertEquals(into, tuple.into());
    assertEquals(value, tuple.value());
  }
