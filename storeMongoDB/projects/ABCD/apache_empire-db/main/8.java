    public static String arrayToString(Object[] array, String separator)
    {
        if (array == null || array.length < 1)
            return null; // Empty
        if (array.length > 1)
        { // multi Column Key
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < array.length; i++)
            {
                if (i>0 && separator!=null)
                    buf.append(separator);
                buf.append(array[i]);
            }
            return buf.toString();
        }
        // Only one member
        return String.valueOf(array[0]);
    }
