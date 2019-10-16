    public void testGetTargetDir()
        throws Exception
    {
        File expected = new File( "/dir2/subdir" );
        File actual = utils.getTargetDirectory( new File( "/dir1/subdir/file" ), new File( "/dir1" ),
                                                new File( "/dir2" ) );
        assertEquals( expected, actual );
    }
