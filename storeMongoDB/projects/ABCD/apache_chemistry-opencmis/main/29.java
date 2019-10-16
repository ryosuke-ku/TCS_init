    public String toString() {
        StringBuilder sb = new StringBuilder(128);

        for (Map.Entry<String, String> entry : entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append('\n');
        }

        return sb.toString();
    }
