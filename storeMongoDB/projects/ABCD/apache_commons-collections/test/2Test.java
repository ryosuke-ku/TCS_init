    public void testSortOrder() {
        final SortedBag<T> bag = decorateBag(new TreeBag<T>(), stringPredicate());
        final String one = "one";
        final String two = "two";
        final String three = "three";
        bag.add((T) one);
        bag.add((T) two);
        bag.add((T) three);
        assertEquals("first element", bag.first(), one);
        assertEquals("last element", bag.last(), two);
        final Comparator<? super T> c = bag.comparator();
        assertTrue("natural order, so comparator should be null", c == null);
    }
