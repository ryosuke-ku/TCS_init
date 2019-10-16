    public void testAddXml() throws ParserConfigurationException
    {
        Options options = new Options();
        options.add(Integer.valueOf(1), "txt", false);
        options.add(Integer.valueOf(2), "txt2", false);

        
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("root");
        doc.appendChild(root);

        // TODO get rid of flags param??
        options.addXml(root, 0);
        Node node = root.getFirstChild();
        assertNotNull("no child was added", node);
        assertEquals("option", node.getNodeName());
        assertEquals("1", node.getAttributes().getNamedItem("value").getNodeValue());
        assertEquals("txt", node.getTextContent());
        
        node = node.getNextSibling();
        assertNotNull("no child was added", node);
        assertEquals("option", node.getNodeName());
        assertEquals("2", node.getAttributes().getNamedItem("value").getNodeValue());
        assertEquals("txt2", node.getTextContent());
    }
