    public static void main( String[] args ) 
    {
    	try {
        //    System.out.println(App.class.getProtectionDomain().getCodeSource().getLocation());
    	//	ResourceBundle.getBundle("env");
    		
    	String action = args[0];
   
        
        AppTest app = new AppTest();
        app.loadProperties();
        
        if (action.equalsIgnoreCase("compare")) {
        	
         	String baselineFile = args[1];
            String snapshotFile = args[2];
            String label = args[3];
        	app.generateComparison(baselineFile, snapshotFile, label);
        	
        } else if (action.equalsIgnoreCase("generate-snapshot")) {
        	
        	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	 System.out.println("Enter label for the snapshot: ");
        	String label = br.readLine();
        	System.out.println("nEnter you jql on one line:");
        	String jql = br.readLine();	
        	
        	app.generateSnapshot(label, jql);
        	
        } else if (action.equalsIgnoreCase("fy13")) {
        	/* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	 System.out.println("Enter label for the snapshot: ");
        	String label = br.readLine();
        	System.out.println("nEnter you jql on one line:");
        	String jql = br.readLine();	
        	*/
        	app.fy13Report("fy13", "filter=25483");
        	
        } else {
        	System.out.println("Unknown action... "+ action);
        	System.exit(-1);
        }	
        
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    }
