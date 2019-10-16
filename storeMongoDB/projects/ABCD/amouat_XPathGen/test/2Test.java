    private void compareXPathResult(final Node n, final String xpath, 
            final XPath xp) {

        Document doc;
        if (n.getNodeType() == Node.DOCUMENT_NODE) {
            doc = (Document) n;
        } else {
            doc = n.getOwnerDocument();
        }
        
        try {
            Node ret = (Node) xp.evaluate(
                    xpath, doc, XPathConstants.NODE);
            assertNotNull(ret);

            if (XPathGen.isText(n)) {
                Node textNode = ret;
                String text = "";
                while (XPathGen.isText(textNode)) {
                    text = text + textNode.getNodeValue();
                    textNode = textNode.getNextSibling();
                }
                
                assertTrue(text + " does not contain " + n.getTextContent(), 
                        text.contains(n.getTextContent()));
            } else {
                assertTrue(
                        ret.getNodeName() + ":" + ret.getNodeValue() 
                        + " is not " + n.getNodeName() + ":" + n.getNodeValue(),
                        n.isSameNode((Node) ret));
            }
        } catch (XPathExpressionException e) {
            fail("Caught exception: " + e.getMessage());
        }

        //Test children
        if (!(n.getNodeType() == Node.ATTRIBUTE_NODE)) {
            NodeList list = n.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                testXPathForNode(list.item(i), xp);
            }
        }
    }
