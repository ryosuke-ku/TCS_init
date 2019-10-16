    public static String replaceAll(String s, String find, String replace)
    {
        if (s == null)
            return null;
        if (replace == null)
            replace = "";
        if (find == null || "".equals(find) || find.equals(replace))
        {   // Nothing to find
            return s;
        }
        int start = s.indexOf(find);
        if (start < 0) 
        {   // Nothing to replace
            return s;
        }    
        // Rebuild string
        StringBuilder b = new StringBuilder(s.length());
        char[] origChars = s.toCharArray();
        int findLength = find.length();
        int copyFrom = 0;
        while (start>= 0)
        {   // append part
            b.append(origChars, copyFrom, start - copyFrom);
            if (replace.length()>0)
                b.append(replace);
            copyFrom = start + findLength;
            start = s.indexOf(find, copyFrom);
        }
        // append the rest
        if (origChars.length > copyFrom)
            b.append(origChars, copyFrom, origChars.length - copyFrom);
        // done
        return b.toString();
    }
