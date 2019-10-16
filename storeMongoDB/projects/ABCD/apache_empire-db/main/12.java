    public static String replace(String source, String find, String replace)
    {
        // Check params
        if (source == null || find == null || find.length()==0)
            return source;
        // Find the character
        int index = source.indexOf(find);
        if (index < 0)
            return source;
        if (replace==null)
            replace="";
        // replace and find again
        int len = find.length();
        return source.substring(0,index)
             + replace
             + replace(source.substring(index+len), find, replace); 
    }
