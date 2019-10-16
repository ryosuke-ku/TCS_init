	public static Set<Problem> buildPointsTo(String application, String main_class, String primordial) throws WalaException, IllegalArgumentException, CancelException, IOException, InvalidClassFileException 
	{					
	    AnalysisScope scope = MyAnalysisScopeReader.makeJavaBinaryAnalysisScope(application, primordial, null);	    
	    ClassHierarchy cha = ClassHierarchy.make(scope);
	    Iterable<Entrypoint> entrypoints = com.ibm.wala.ipa.callgraph.impl.Util.makeMainEntrypoints(scope, cha, "L"+main_class);
	    AnalysisOptions options = new AnalysisOptions(scope, entrypoints);   
	    options.setReflectionOptions(ReflectionOptions.NONE);
	    
	    ContextSelector scjContextSelector = new ScjContextSelector(cha);	    
		com.ibm.wala.ipa.callgraph.CallGraphBuilder builder = ZeroXCFABuilder(options, new AnalysisCache(), cha, scope, scjContextSelector);	    
	    CallGraph cg = builder.makeCallGraph(options,null);
	    PointerAnalysis pointerAnalysis = builder.getPointerAnalysis(); 	   
	    BasicHeapGraph bhg = new BasicHeapGraph(pointerAnalysis, cg); 
	    HashSet<Problem> problems = new HashSet<Problem>();    
	    HashSet<MemoryAnnotation> annotations = new HashSet<MemoryAnnotation>();
	    
	    runAnalysis(bhg.getPointerAnalysis(), (HeapGraph)bhg, problems, annotations);	
	    	    
	    /* Generate annotations */
	    System.out.print("Annotations:\n");
	    
	    //RunsIn Annotations
	    Iterator<Entry<IMethod, ScjScopeStack>> pItr = ((ScjContextSelector)scjContextSelector).methodScopeMap.entrySet().iterator();		
		
		while ( pItr.hasNext() )
		{	
			Entry<IMethod, ScjScopeStack> entry = pItr.next();
			String className = entry.getKey().getDeclaringClass().getName().toString();
			if( entry.getValue().size() == 1) 
					annotations.add(new MemoryAnnotationRunsIn(entry.getValue().getLast(), entry.getKey()));
			else
					annotations.add(new MemoryAnnotationRunsIn(null, entry.getKey(), entry.getValue()));
		}
		
		Iterator<Entry<IClass, ScjScopeStack>> pItr2 = ((ScjContextSelector)scjContextSelector).classScopeMap.entrySet().iterator();	
		
		//Scope - Class Annotations
		while ( pItr2.hasNext() )
		{	
			Entry<IClass, ScjScopeStack> entry = pItr2.next();
			annotations.add(new MemoryAnnotationScope(entry.getValue().getLast(),entry.getKey(),null));
		}

		//DefineScope Annotations
		Iterator<ScjScopeStack> pItr3 = ((ScjContextSelector)scjContextSelector).scopeStacks.iterator();	
		
		while ( pItr3.hasNext() )
		{	
			ScjScopeStack ss = pItr3.next();					
			annotations.add(new MemoryAnnotationDefineScope(ss));			
		}
	    		
	    //Print Annotations
		MemoryAnnotation meman;
		String className;
		
		for (java.util.Iterator<MemoryAnnotation> i = annotations.iterator(); i.hasNext(); ) {
			meman = i.next();
			className = meman.getClassName();
			
			if ( !className.startsWith("Ljava") && !className.startsWith("Lcom") && !className.startsWith("Ljoprt") ) {				
				System.out.print(meman.toString());
			}			
		}
		
		
		return problems; 
  }	
