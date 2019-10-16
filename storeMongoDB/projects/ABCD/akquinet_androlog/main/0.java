    public static String read(InputStream in) throws IOException {
        InputStreamReader isReader = new InputStreamReader(in, "UTF-8");
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer out = new StringBuffer();
        char[] c = new char[4096];
        for (int n; (n = reader.read(c)) != -1;) {
            out.append(new String(c, 0, n));
        }
        reader.close();

        return out.toString();
    }
