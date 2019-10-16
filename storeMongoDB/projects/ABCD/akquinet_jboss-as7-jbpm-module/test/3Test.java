    public void basicTransactionRollbackTest() {
        Environment env = initializeEnvironment(emf);
        Object tm = env.get( EnvironmentName.TRANSACTION_MANAGER );
        TransactionManager txm = new JtaTransactionManager( env.get( EnvironmentName.TRANSACTION ),
                env.get( EnvironmentName.TRANSACTION_SYNCHRONIZATION_REGISTRY ),
                tm ); 
           
        // Create linked transactionTestObjects 
        TransactionTestObject mainObject = new TransactionTestObject();
        mainObject.setName("main");
        TransactionTestObject subObject = new TransactionTestObject();
        subObject.setName("sub");
        mainObject.setSubObject(subObject);
       
        EntityManager em = emf.createEntityManager();
        try { 
            boolean txOwner = txm.begin();
            
            boolean notTxOwner = txm.begin();
            em.persist(mainObject);
            txm.rollback(notTxOwner);
        
            em.persist(subObject);
            txm.rollback(txOwner);
        }
        catch( Exception e ) { 
            fail("There should not be an exception thrown here: " + e.getMessage());
        }
        
    }
