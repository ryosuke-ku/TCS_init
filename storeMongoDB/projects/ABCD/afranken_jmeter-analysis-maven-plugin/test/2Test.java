  public void testValueCount() {
    ValueCount vc = new ValueCount(11);
    assertEquals(11L, vc.getValue());
    assertEquals(0, vc.getCount());

    vc.increment();
    assertEquals(1, vc.getCount());
  }
