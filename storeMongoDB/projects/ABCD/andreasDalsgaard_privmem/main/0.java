	public static void main(String[] args) throws WalaException {
		String primordial, main;
		Properties p = CommandLine.parse(args);
		int problemCounter = 0;
		boolean appAlone = false;
		
		if (p.getProperty("application") == null ) 		
			util.print_usage();
 
		if (p.getProperty("main") != null)
			main = p.getProperty("main");
		else
			main = "Main";
		
		if (p.getProperty("primordial") != null)
			primordial = p.getProperty("primordial");
		else
			primordial = null;
		
		if (p.getProperty("appAlone") != null)
		{
			if (p.getProperty("primordial") != null)
				util.print_usage();
			
			String appAloneStr = p.getProperty("appAlone");
			
			if (appAloneStr.equals("true") || appAloneStr.equals("True") || appAloneStr.equals("1"))
				appAlone = true;
		}
			
		try {
			Set<Problem> problems = buildPointsTo(p.getProperty("application"), main, primordial, appAlone);
			
			Iterator<Problem> pItr = problems.iterator();			
			System.out.print("Problems:\n");
			
			while ( pItr.hasNext() )
			{	
				String strNext = pItr.next().toString();				
				if (!strNext.isEmpty()) {
					System.out.print(strNext+"\n");
					problemCounter++;
				}
			}
			
			System.out.print("Nr. of problems: "+problemCounter+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
