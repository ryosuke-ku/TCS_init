    public synchronized <T> T execute(Command<T> command) {
        boolean transactionOwner = false;
        try {
            initKsession( this.sessionInfo.getId(),
                          this.marshallingHelper.getKbase(),
                          this.marshallingHelper.getConf() );
            
            transactionOwner = txm.begin();            
            
            this.jpm.beginCommandScopedEntityManager();

            registerRollbackSync();

            T result = null;
            if ( !( command instanceof DisposeCommand) ) {
                result = commandService.execute((GenericCommand<T>) command);
            }

            txm.commit(transactionOwner);

            return result;

        }catch (RuntimeException re){
            rollbackTransaction(re, transactionOwner);
            throw re;
        } catch ( Exception t1 ) {
            rollbackTransaction(t1, transactionOwner);
            throw new RuntimeException("Wrapped exception see cause", t1);
        } finally {
            if ( command instanceof DisposeCommand ) {
                commandService.execute((GenericCommand<T>) command);
                this.jpm.dispose();
            }            
        }
    }
