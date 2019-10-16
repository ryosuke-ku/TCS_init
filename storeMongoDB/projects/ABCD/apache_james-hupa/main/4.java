    public static String decodeText(String s) {
        String ret = s;
        try {
            ret = MimeUtility.decodeText(s);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        ret =  ret
          // Remove quotes around names in email addresses
          .replaceFirst("^[<\"' ]+([^\"<>]*)[>\"' ]+<", "$1 <");
        return ret;
    }
