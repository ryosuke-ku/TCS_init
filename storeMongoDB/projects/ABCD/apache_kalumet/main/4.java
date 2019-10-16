    public void addEnvironment( Environment environment )
        throws ModelObjectAlreadyExistsException
    {
        if ( this.getEnvironment( environment.getName() ) != null )
        {
            throw new ModelObjectAlreadyExistsException( "Environment name already exists in Kalumet configuration." );
        }
        this.environments.add( environment );
    }
