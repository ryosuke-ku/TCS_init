    public void readTest()
    {
        for ( Pointer<Object> ptr : MemoryManager.getMemoryManager().getPointers() )
        {
            if ( !ptr.isFree() )
            {
                byte[] res = MemoryManager.retrieve( ptr );
                assertNotNull( res );
                assertEquals( new String( payload ), new String( res ) );
            }
        }
    }
