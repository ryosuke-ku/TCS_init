    public void fy13Report (String label, String jql) {
    	
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();		
		MTJiraClient client = new MTJiraClient();	
		jiraIssues =  client.retrieveJiraIssues(jql);
		
		FY13EpicsManager manager = new FY13EpicsManager();
		System.out.println("Aggragating data");
		manager.aggregateData(jiraIssues);
		System.out.println("Printing...");
		manager.printEpics();
    
    }
