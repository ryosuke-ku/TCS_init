    public static ValueObjectMap PmOptionsStringToMap(String pmOptions) {
        if(pmOptions.equals("")) {
            return new ValueObjectMap();
        }
        HashMap<String, String> map = new HashMap<String, String>();
        String[] tokens = pmOptions.split(",");
        for (String token : tokens) {
            String[] pair = token.split("=");
            if (pair.length == 2) { // key=value setting
                pair[1] = (pair[1] == null ? "" : pair[1]);
                // ignore illegal settings
                if (pair[0].trim().length() > 0 && pair[1].trim().length() > 0)
                    map.put(pair[0], pair[1]);
            } else { // only key setting
                map.put(pair[0], "");
            }
        }
        return new ValueObjectMap(map, false);
    }
