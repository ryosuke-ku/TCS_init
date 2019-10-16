    public void testStatisticsWhenCacheNotUsedYet()
        throws Exception
    {

        NamedList stats = solrOffHeapCache.getStatistics();
        assertNotNull( stats );
        assertEquals( 0l, stats.get( "lookups" ) );
        assertEquals( 0l, stats.get( "evictions" ) );
        assertEquals( 0l, stats.get( "hits" ) );
        assertEquals( 0l, stats.get( "inserts" ) );
    }
