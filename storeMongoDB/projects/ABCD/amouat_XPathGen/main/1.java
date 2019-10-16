    public static boolean nodeIsEmptyText(final Node n) {
        return (n.getNodeType() == Node.TEXT_NODE 
            && n.getNodeValue().length() == 0);
    }
