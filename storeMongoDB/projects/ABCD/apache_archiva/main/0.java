    public int compare( AbstractRepositoryConfiguration o1, AbstractRepositoryConfiguration o2 )
    {
        if ( o1 == null && o2 == null )
        {
            return 0;
        }

        if ( o1 == null )
        {
            return -1;
        }

        if ( o2 == null )
        {
            return 1;
        }

        return o1.getId().compareToIgnoreCase( o2.getId() );
    }
