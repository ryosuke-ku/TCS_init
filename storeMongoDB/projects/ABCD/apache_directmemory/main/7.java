    public NamedList getStatistics()
    {
        NamedList lst = new SimpleOrderedMap();
        synchronized ( this )
        {
            lst.add( "lookups", lookups );
            lst.add( "hits", hits );
            lst.add( "hitratio", calcHitRatio( lookups, hits ) );
            lst.add( "inserts", inserts );
            lst.add( "evictions", evictions );
            lst.add( "size", cacheService.entries() );
        }

        lst.add( "warmupTime", warmupTime );

        long clookups = stats.lookups.get();
        long chits = stats.hits.get();
        lst.add( "cumulative_lookups", clookups );
        lst.add( "cumulative_hits", chits );
        lst.add( "cumulative_hitratio", calcHitRatio( clookups, chits ) );
        lst.add( "cumulative_inserts", stats.inserts.get() );
        lst.add( "cumulative_evictions", stats.evictions.get() );

        return lst;
    }
