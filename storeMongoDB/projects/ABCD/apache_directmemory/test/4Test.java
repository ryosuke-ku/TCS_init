    public void testCacheSingletonWithImportedObject()
    {
        SimpleObject obj1 = new SimpleObject( "1", "Object One" );
        SimpleObject obj2 = new SimpleObject( "2", "Object Two" );
        Cache.init( 1, Ram.Mb( 16 ) );
        Cache.scheduleDisposalEvery( Every.seconds( 1 ) );
        Cache.dump();

        Pointer p1 = Cache.put( "1", obj1 );
        Pointer p2 = Cache.put( "2", obj2 );
        Object result1 = Cache.retrieve( "1" );
        Object result2 = Cache.retrieve( "2" );

        Cache.dump();
        Monitor.dump( "cache" );

        assertEquals( obj1, result1 );
        assertEquals( obj2, result2 );
    }
