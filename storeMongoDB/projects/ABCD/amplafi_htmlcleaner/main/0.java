    public static String escapeXml(String s, boolean advanced, boolean recognizeUnicodeChars, boolean translateSpecialEntities, 
                                   boolean isDomCreation, boolean transResCharsToNCR, boolean translateSpecialEntitiesToNCR) {
        if (s != null) {
    		int len = s.length();
    		StringBuilder result = new StringBuilder(len);

    		for (int i = 0; i < len; i++) {
    			char ch = s.charAt(i);

    			SpecialEntity code;
    			if (ch == '&') {
    				if ( (advanced || recognizeUnicodeChars) && (i < len-1) && (s.charAt(i+1) == '#') ) {
    					i = convertToUnicode(s, isDomCreation, recognizeUnicodeChars, translateSpecialEntitiesToNCR, result, i+2);
    				} else if ((translateSpecialEntities || advanced) &&
				        (code = SpecialEntities.INSTANCE.getSpecialEntity(s.substring(i, i+Math.min(10, len-i)))) != null) {
			            if (translateSpecialEntities && code.isHtmlSpecialEntity()) {
                            if (recognizeUnicodeChars) {
                                result.append( (char)code.intValue() );
                            } else {
                                result.append( code.getDecimalNCR() );
                            }
							i += code.getKey().length() + 1;
						} else if (advanced ) {
					        result.append(transResCharsToNCR ? code.getDecimalNCR() : code.getEscaped(isDomCreation));
		                    i += code.getKey().length()+1;
			            } else {
			                result.append(transResCharsToNCR ? getAmpNcr() : "&amp;");
			            }
    				} else {
    				    result.append(transResCharsToNCR ? getAmpNcr() : "&amp;");
    				}
    			} else if ((code = SpecialEntities.INSTANCE.getSpecialEntityByUnicode(ch)) != null ) {
    			    result.append(transResCharsToNCR ? code.getDecimalNCR() : code.getEscaped(isDomCreation));
    			} else {
    				result.append(ch);
    			}
    		}

    		return result.toString();
    	}

    	return null;
    }
