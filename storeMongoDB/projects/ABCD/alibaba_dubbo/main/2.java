    public static boolean isMatchGlobPattern(String pattern, String value) {
        if ("*".equals(pattern))
        	return true;
    	if((pattern == null || pattern.length() == 0) 
    			&& (value == null || value.length() == 0)) 
    		return true;
    	if((pattern == null || pattern.length() == 0) 
    			|| (value == null || value.length() == 0)) 
        	return false;
        
        int i = pattern.lastIndexOf('*');
        // –v—LQ“¯†
        if(i == -1) {
            return value.equals(pattern);
        }
        // ¯†İ––”ö
        else if (i == pattern.length() - 1) {
            return value.startsWith(pattern.substring(0, i));
        }
