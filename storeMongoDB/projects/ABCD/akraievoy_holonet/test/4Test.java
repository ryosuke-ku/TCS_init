  public void testLogistic() throws Exception {
    Interpolate.Fun fun = Interpolate.norm(2, 8, 3, 7, Interpolate.LOGISTIC);
    assertEquals(3.000, fun.apply(0), 0.001);
    assertEquals(3.001, fun.apply(1), 0.001);
    assertEquals(3.010, fun.apply(2), 0.001);
    assertEquals(3.072, fun.apply(3), 0.001);
    assertEquals(3.477, fun.apply(4), 0.001);
    assertEquals(5.000, fun.apply(5), 0.001);
    assertEquals(6.523, fun.apply(6), 0.001);
    assertEquals(6.928, fun.apply(7), 0.001);
    assertEquals(6.990, fun.apply(8), 0.001);
    assertEquals(6.999, fun.apply(9), 0.001);
    assertEquals(6.999, fun.apply(10), 0.001);

    fun = Interpolate.norm(8, 2, 3, 7, Interpolate.LOGISTIC);
    assertEquals(6.999, fun.apply(0), 0.001);
    assertEquals(6.999, fun.apply(1), 0.001);
    assertEquals(6.990, fun.apply(2), 0.001);
    assertEquals(6.928, fun.apply(3), 0.001);
    assertEquals(6.523, fun.apply(4), 0.001);
    assertEquals(5.000, fun.apply(5), 0.001);
    assertEquals(3.477, fun.apply(6), 0.001);
    assertEquals(3.072, fun.apply(7), 0.001);
    assertEquals(3.010, fun.apply(8), 0.001);
    assertEquals(3.001, fun.apply(9), 0.001);
    assertEquals(3.000, fun.apply(10), 0.001);

    fun = Interpolate.norm(8, 2, 7, 3, Interpolate.LOGISTIC);
    assertEquals(3.000, fun.apply(0), 0.001);
    assertEquals(3.001, fun.apply(1), 0.001);
    assertEquals(3.010, fun.apply(2), 0.001);
    assertEquals(3.072, fun.apply(3), 0.001);
    assertEquals(3.477, fun.apply(4), 0.001);
    assertEquals(5.000, fun.apply(5), 0.001);
    assertEquals(6.523, fun.apply(6), 0.001);
    assertEquals(6.928, fun.apply(7), 0.001);
    assertEquals(6.990, fun.apply(8), 0.001);
    assertEquals(6.999, fun.apply(9), 0.001);
    assertEquals(6.999, fun.apply(10), 0.001);
  }
