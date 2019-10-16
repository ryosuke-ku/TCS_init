    public static String nullIfEmpty(Object value)
    {
        if (value==null)
            return null;
        String strval = value.toString();
        return ((strval.length()==0) ? null : strval);   
    }
