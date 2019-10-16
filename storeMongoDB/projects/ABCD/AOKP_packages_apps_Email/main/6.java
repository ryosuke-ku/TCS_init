    public static String getHeaderParameter(String header, String name) {
        if (header == null) {
            return null;
        }
        String[] parts = unfold(header).split(";");
        if (name == null) {
            return parts[0].trim();
        }
        String lowerCaseName = name.toLowerCase();
        for (String part : parts) {
            if (part.trim().toLowerCase().startsWith(lowerCaseName)) {
                String[] parameterParts = part.split("=", 2);
                if (parameterParts.length < 2) {
                    return null;
                }
                String parameter = parameterParts[1].trim();
                if (parameter.startsWith("\"") && parameter.endsWith("\"")) {
                    return parameter.substring(1, parameter.length() - 1);
                } else {
                    return parameter;
                }
            }
        }
        return null;
    }
