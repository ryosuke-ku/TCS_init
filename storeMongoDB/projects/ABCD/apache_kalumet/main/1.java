    public Property getProperty( String name )
    {
        for ( Iterator propertyIterator = this.getProperties().iterator(); propertyIterator.hasNext(); )
        {
            Property property = (Property) propertyIterator.next();
            if ( property.getName().equals( name ) )
            {
                return property;
            }
        }
        return null;
    }
