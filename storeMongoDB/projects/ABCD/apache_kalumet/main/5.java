    public Environment getEnvironment( String name )
    {
        for ( Iterator environmentIterator = this.getEnvironments().iterator(); environmentIterator.hasNext(); )
        {
            Environment environment = (Environment) environmentIterator.next();
            if ( environment.getName().equals( name ) )
            {
                return environment;
            }
        }
        return null;
    }
