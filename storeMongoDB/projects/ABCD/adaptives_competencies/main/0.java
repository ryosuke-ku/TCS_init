	public static Topic parseCSV(String topicTitle, 
								 int topicPlacement, 
								 String csv) throws ParseException {
		
		CSVLineParser lineParser = new CSVLineParser();
		Topic topic = new Topic(topicTitle, "desc", "resources");
		topic.placement = topicPlacement;
		List<Level> levels;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new StringReader(csv));
			int lineCnt = 0;
			String line = reader.readLine();
			lineCnt++;
			levels = parseLevels(line);
			CompetencyGroup competencyGroup = null;
			int competencyGroupPlacement = 0;
			while((line = reader.readLine()) != null) {
				lineCnt++;
				String tokens[] = lineParser.parse(line);
				int competencyPlacement = 0;
				for(int i=0; i<tokens.length; i++) {
					String token = tokens[i].trim();
					if(!token.equals("")) {
						if(i == 0) {
							if(competencyGroup != null) {
								topic.competencyGroups.add(competencyGroup);
							}							
							competencyGroup = new CompetencyGroup(token, "desc", "resources");
							competencyGroup.placement = competencyGroupPlacement++;
							competencyGroup.topic = topic;
						} else if(i > levels.size()) {
							String msg = "Unspecified level at line " + lineCnt + " token " + i + "  '" + token + "'";
							throw new ParseException(msg);
						} else {
							Competency competency = new Competency(token, 
																   "desc", 
																   competencyGroup, 
																   levels.get(i-1), 
																   "resources");
							competency.placement = competencyPlacement++;
							competencyGroup.competencies.add(competency);
						}
					}					
				}
			}
			topic.competencyGroups.add(competencyGroup);
		} catch(IOException ioe) {
			//TOOD: Log
		} finally {
			if(reader != null) {
				try { reader.close(); } catch(IOException ioe) {}
			}
		}
		return topic;
	}
