  public void testGet() throws Exception {
    assertEquals(4, eData.getNonDefCount());

    assertEquals(12.0, eData.get(3, 4));
    assertEquals(11.0, eData.get(2, 4));
    assertEquals(0.0, eData.get(1, 3));

    assertEquals(12.0, eData.get(4, 3));
    assertEquals(11.0, eData.get(4, 2));
    assertEquals(0.0, eData.get(3, 1));

    assertEquals(0.0, eData.get(2, 2));
    assertEquals(0.0, eData.get(2, 1));
    assertEquals(0.0, eData.get(1, 2));

    eData.set(1, 3, 13.0);

    assertEquals(6, eData.getNonDefCount());

    assertEquals(12.0, eData.get(3, 4));
    assertEquals(11.0, eData.get(2, 4));
    assertEquals(13.0, eData.get(1, 3));

    assertEquals(12.0, eData.get(4, 3));
    assertEquals(11.0, eData.get(4, 2));
    assertEquals(13.0, eData.get(3, 1));

    assertEquals(0.0, eData.get(2, 2));
    assertEquals(0.0, eData.get(2, 1));
    assertEquals(0.0, eData.get(1, 2));

    final EdgeData.ElemIterator ndi = eData.nonDefIterator();

    checkTuple(ndi, 1, 3, 13.0);
    checkTuple(ndi, 2, 4, 11.0);
    checkTuple(ndi, 3, 1, 13.0);
    checkTuple(ndi, 3, 4, 12.0);
    checkTuple(ndi, 4, 2, 11.0);
    checkTuple(ndi, 4, 3, 12.0);
    assertFalse(ndi.hasNext());

    assertEquals(13.0, eData.set(1, 3, 0.0));
    assertEquals(0.0, eData.get(1, 3));

    assertEquals(4, eData.getNonDefCount());
  }
