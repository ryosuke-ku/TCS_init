    public void generateDefaultDDRTypes()
    {
        //check if all DDR categories are created on bootstrapping this agent
        for ( DDRTypeCategory ddrCategory : DDRTypeCategory.values() )
        {
            if ( !ddrCategory.equals( DDRTypeCategory.OTHER ) ) //ignore other
            {
                try
                {
                    DDRType ddrType = DDRType.getDDRType( ddrCategory );
                    if ( ddrType == null )
                    {
                        ddrType = new DDRType();
                        ddrType.setCategory( ddrCategory );
                        ddrType.setName( "DEFAULT - " + ddrCategory.name() );
                        ddrType.createOrUpdate();
                    }
                }
                catch ( Exception e )
                {
                    log.severe( String.format( "DDRType creation failed for type: %s. Error: %s", ddrCategory.name(),
                        e.getLocalizedMessage() ) );
                }
            }
        }
    }
