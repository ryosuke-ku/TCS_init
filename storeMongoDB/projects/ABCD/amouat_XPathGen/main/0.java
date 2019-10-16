    public static String getXPath(final Node n) {
 
        String xpath;
        
        if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
            //Slightly special case for attributes as they are considered to
            //have no parent
            ((Attr) n).getOwnerElement();
            xpath = getXPath(((Attr) n).getOwnerElement())
                 + "/@" + n.getNodeName();
            
        } else if (n.getNodeType() == Node.DOCUMENT_NODE) {
            
            xpath = "/";
        } else if (n.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
            
            throw new IllegalArgumentException(
                    "DocumentType nodes cannot be identified with XPath");
            
        } else if (n.getParentNode().getNodeType() == Node.DOCUMENT_NODE) {
            
            ChildNumber cn = new ChildNumber(n);
            xpath = "/node()[" + cn.getXPath() + "]"; 
            
        } else {

            ChildNumber cn = new ChildNumber(n);

            xpath = getXPath(n.getParentNode()) 
                + "/node()[" + cn.getXPath() + "]";
        }
        
        return xpath;
    }
