	public Set<String> expand(String [] tokens) throws TooComplexWordException {
		List<String> result = new ArrayList<String>();
		result.add("");
		
		for (int i=0; i<tokens.length; i++) {
			
			if (i > 0) {
				result = append(result, " ");
			}
		
			if (tokens[i].matches("\\d+")) {
				if ((!tokens[i].startsWith("0")) && (Long.parseLong(tokens[i]) <= 9000)) {
					// [20 le] -> [<20 omastav> le]
					if ((i == tokens.length - 2) && (tokens[i+1].matches("\\p{Ll}{1,3}"))) {
						result = appendAndFork(result, numbersOmastav.get(tokens[i]));
						
					} else {
						result = appendAndFork(result, numbersNimetav.get(tokens[i]));
					}
				} else {
					if (tokens[i].length() > 4) {
						throw new TooComplexWordException("Number too long: " + tokens[i]);
					}
					//spell by numbers
					for (char digit : tokens[i].toCharArray()) {
						result = appendAndFork(result, numbersNimetav.get(String.valueOf(digit)));
					}
					
				}
				continue;
			}
			
			if ((tokens[i].length() == 1) &&
					(spell.containsKey(tokens[i])) ||
					((i == 0) && (spell.containsKey(tokens[i].toUpperCase())))) {
				result = appendAndFork(result, spell.get(tokens[i].toUpperCase()));
				
				continue;
			}
			
			if (abbreviations.containsKey(tokens[i])) {
				List<String> abbr = abbreviations.get(tokens[i]);
				if (abbr.get(0).equals("?")) {
					result = append(result, tokens[i].toLowerCase());
				} else {
					result = appendAndFork(result, abbr);
				}
				continue;
			}
			
			if (dict.containsKey(tokens[i])) {
				result = appendAndFork(result, dict.get(tokens[i]));
				continue;
			}
			
			boolean found = false;
			for (String foreign: dict.keySet()) {
				if (tokens[i].matches(foreign + "\\p{Ll}{1,3}")) {
					
					result = appendAndFork(result, dict.get(foreign));
					result = append(result, tokens[i].substring(foreign.length()));
					
					found = true;
					break;
				}
			}
			if (found) continue;
			
			//HACK: last short lowercase part of multipart word is probably a suffix
			if ((i >0) && (i == tokens.length - 1) && (tokens[i].matches("\\p{Ll}{1,3}"))) {
				for (int j = 0; j < result.size(); j++) {
					result.set(j, result.get(j).substring(0,  result.get(j).length() - 1));
				}
				
			}
			result = append(result, tokens[i]);
			
			
			
		}
		return new HashSet<String>(result);
	}
