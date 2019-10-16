    public static String hex(int b) {
        String result = Integer.toHexString(b);
        if (result.length() == 1)
            result = "0" + result;
        else
            result = result.substring(result.length() - 2);
        return result;
    }
