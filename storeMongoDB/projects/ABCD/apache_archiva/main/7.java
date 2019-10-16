    public static String getVersionFromGenericSnapshot( String version )
    {
        Matcher m = GENERIC_SNAPSHOT_PATTERN.matcher( version );
        if ( m.matches() )
        {
            return m.group( 1 );
        }
        return version;
    }
