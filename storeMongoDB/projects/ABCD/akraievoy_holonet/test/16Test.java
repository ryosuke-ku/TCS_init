      public void visit(final int from, final int into, final double e) {
        final Object[] expected = visitNonDefRes.get(resPos[0]);
        assertEquals(((Number) expected[0]).intValue(), from);
        assertEquals(((Number) expected[1]).intValue(), into);
        assertEquals(((Number) expected[2]).doubleValue(), e);
        resPos[0] += 1;
      }
