    public Agent getAgent( String id )
    {
        for ( Iterator agentIterator = this.getAgents().iterator(); agentIterator.hasNext(); )
        {
            Agent agent = (Agent) agentIterator.next();
            if ( agent.getId().equals( id ) )
            {
                return agent;
            }
        }
        return null;
    }
