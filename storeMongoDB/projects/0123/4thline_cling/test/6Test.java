                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        super.startElement(uri, localName, qName, attributes);
                        if ("urn:my-vendor-extension".equals(uri) && localName.equals("foo")) {
                            tests[0] = true;
                        }
                    }
