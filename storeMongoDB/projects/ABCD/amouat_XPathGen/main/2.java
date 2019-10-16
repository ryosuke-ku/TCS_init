    public static boolean isText(final Node node) {
     
        boolean ret = false;
        if (node != null 
                && (node.getNodeType() == Node.TEXT_NODE
                || node.getNodeType() == Node.CDATA_SECTION_NODE)) {
            ret = true;
        }
        return ret;
    }
