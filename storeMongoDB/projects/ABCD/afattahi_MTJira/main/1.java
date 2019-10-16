    public void generateComparison(String baselineFile, String snapshotFile, String label) {
    	
        
        JiraFileLoader loader = new JiraFileLoader();
        ComparisonEngine engine = new ComparisonEngine(loader.load(baselineFile),loader.load(snapshotFile));

			engine.comparison();
			engine.createOutFile(loader.getReport1FileName(label));
    	
    }
