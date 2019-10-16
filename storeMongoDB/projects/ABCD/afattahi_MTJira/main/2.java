    public void generateSnapshot(String label, String jql) {
    	
		List<JiraIssue> jiraIssues = new ArrayList<JiraIssue>();		
		MTJiraClient client = new MTJiraClient();	
		jiraIssues =  client.retrieveJiraIssues(jql);
		
		JiraFileLoader loader = new JiraFileLoader();
		
		loader.generateExcelSheet(loader.getSnapshotFileName(label),jiraIssues,jql);
    	
    }
