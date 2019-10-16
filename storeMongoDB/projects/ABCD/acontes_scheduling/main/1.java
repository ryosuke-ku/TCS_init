    public static boolean checkOSName(String exp) {
        if (exp == null) {
            System.err.println("Given OS name was NULL");
            return false;
        }

        String localOS = System.getProperty("os.name");

        try {
            Pattern regex = Pattern.compile(exp, Pattern.CASE_INSENSITIVE);
            Matcher regexMatcher = regex.matcher(localOS);
            return (regexMatcher.find());
        } catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
