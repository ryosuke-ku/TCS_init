    private void testXPathForNode(final Node n, final XPath xp) {
        
        if (n.getNodeType() != Node.DOCUMENT_TYPE_NODE) {
            String xpath = XPathGen.getXPath(n);
            compareXPathResult(n, xpath, xp);
        }
    }
