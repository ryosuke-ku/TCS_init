    public void forEachButLast() {
        final List<Integer> listA = new ArrayList<Integer>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<Integer>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<List<Integer>>();
        col.add(listA);
        col.add(listB);
        List<Integer> last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        assertTrue(listA.isEmpty() && !listB.isEmpty());
        assertSame(listB, last);

        try {
            IteratorUtils.forEachButLast(col.iterator(), null);
            fail("expecting NullPointerException");
        } catch (NullPointerException npe) {
            // expected
        }

        IteratorUtils.forEachButLast(null, testClosure);

        // null should be OK
        col.add(null);
        col.add(null);
        last = IteratorUtils.forEachButLast(col.iterator(), testClosure);
        assertNull(last);
    }
