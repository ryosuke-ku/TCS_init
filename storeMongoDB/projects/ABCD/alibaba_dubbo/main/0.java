    public static List<String> interpolate(List<String> expressions, Map<String, String> params) {
        List<String> ret = new ArrayList<String>();
        
        if(null == expressions || expressions.isEmpty()) {
            return ret;
        }
        
        for(String expr : expressions) {
            ret.add(interpolate(expr, params));
        }
        
        return ret;
    }
