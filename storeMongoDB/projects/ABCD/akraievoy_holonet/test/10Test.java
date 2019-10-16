  public void testSetAndClear() {
    final Random random = new Random(0xDEADBEEF);
    final int size = 128;
    final EdgeData d = EdgeDataFactory.sparse(true, 0.0, size);
    final int ops = size * size / 3;
    final int[] froms = new int[ops];
    final int[] intos = new int[ops];

    for (int op = 0; op < ops; op++) {
      d.set(
          froms[op] = random.nextInt(size),
          intos[op] = random.nextInt(size),
          1.0
      );
    }

    assertTrue(d.getNonDefCount() > 0);

    for (int op = 0; op < ops; op++) {
      //  yup: the data is symmetric so it's akay to swap the indices
      d.set(
          intos[op],
          froms[op],
          0.0
      );
    }

    for (int from = 0; from < size; from ++ ) {
      for (int into = 0; into < size; into ++ ) {
        assertEquals(0.0, d.get(from, into));
      }
    }

    assertEquals(0, d.getNonDefCount());
  }
