    public static String[] condense(String... types) {
        if (types.length <= 1)
            return types;
        List<String> res = new ArrayList<String>();
        Arrays.sort(types, getComparator());
        for (String t : types) {
            if (!contains(t, res, true))
                res.add(t);
        }
        for (int n = 0; n < res.size(); n++) {
            String t = res.get(n);
            if (contains(t, res, false))
                res.remove(t);
        }
        return res.toArray(new String[res.size()]);
    }
