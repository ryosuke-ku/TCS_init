    public static String concatenateClauses(String... clauses) {
        StringBuilder builder = new StringBuilder();
        for (String clause : clauses) {
            if (!TextUtils.isEmpty(clause)) {
                if (builder.length() > 0) {
                    builder.append(" AND ");
                }
                builder.append("(");
                builder.append(clause);
                builder.append(")");
            }
        }
        return builder.toString();
    }
