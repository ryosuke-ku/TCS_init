    public static Element parseHeaderMetadata(String header) throws ParseException {
        ManifestMetadataParser parser = new ManifestMetadataParser();
        parser.addElement(new Element("iPOJO", ""));
        parser.parseElements(header);
        if (parser.m_elements.length != 1) {
            throw new ParseException("Error in parsing, root element not found : " + header);
        }
        return parser.m_elements[0];
    }
