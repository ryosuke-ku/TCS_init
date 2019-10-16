	public void testParseCSVUnsuccessfullIncorrectLevels() throws Exception {
	String csv =     ",Level 6 , , ," +
				     "sg1, c111, c112, c113," +
				     ", c121, c122, c123," +
				     ", c131, c132, c133," +
				     "sg2, c211, c212, c213," +
				     ", c211, c212, c213," +
				     "sg3, c311, c312, c313,";
		
		Topic topic = DataUtils.parseCSV("test topic", 0, csv);
		assertNotNull(topic);
	}
"Core CS Description", 
    							"Core CS Resources");
    	topic.placement = null;
    	topic.levels.add(level1);
    	topic.levels.add(level2);
    	topic.levels.add(level3);
    	
    	topic.save();
    }
