    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LV").append(layoutFormatVersion).append(":")
            .append(",Type:").append(managerFactoryCls).append(":")
            .append(managerVersion);
        return sb.toString();
    }
