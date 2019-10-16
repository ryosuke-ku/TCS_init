    public static boolean checkJavaProperty(String propertyName, String propertyValue) {
        if (propertyName == null) {
            System.err.println("Given property Name was NULL");
            return false;
        }
        if (propertyValue == null) {
            System.err.println("Given property Value was NULL");
            return false;
        }
        try {
            String vmPropValue = System.getProperty(propertyName);
            Pattern regex = Pattern.compile(propertyValue, Pattern.CASE_INSENSITIVE);
            Matcher regexMatcher = regex.matcher(vmPropValue);
            return (regexMatcher.find());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
