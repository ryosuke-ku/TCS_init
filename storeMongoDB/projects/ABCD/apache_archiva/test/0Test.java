    public void testComparator()
    {
        Comparator<AbstractRepositoryConfiguration> comparator = new RepositoryConfigurationComparator();

        assertEquals( 0, comparator.compare( null, null ) );
        assertEquals( 1, comparator.compare( createRepository( "id" ), null ) );
        assertEquals( -1, comparator.compare( null, createRepository( "id" ) ) );
        assertEquals( 0, comparator.compare( createRepository( "id1" ), createRepository( "id1" ) ) );
        assertEquals( -1, comparator.compare( createRepository( "id1" ), createRepository( "id2" ) ) );
        assertEquals( 1, comparator.compare( createRepository( "id2" ), createRepository( "id1" ) ) );
    }
