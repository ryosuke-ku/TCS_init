    public static String validate(String s)
    {
        if (s==null)
            return null;
        s = s.trim();
        if (s.length()==0)
            return null;
        return s;
    }
