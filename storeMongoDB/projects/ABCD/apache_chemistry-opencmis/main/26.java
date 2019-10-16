    public void parse(String parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters string must be set!");
        }

        for (String line : parameters.split("\n")) {
            putLine(line);
        }
    }
