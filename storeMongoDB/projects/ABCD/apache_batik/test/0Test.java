    public TestReport runImpl() throws Exception {
        String TEST_URI = (new File("samples/anne.svg")).toURL().toString();

        TestTranscoder t = new TestTranscoder();

        TranscoderOutput out = new TranscoderOutput(new StringWriter());

        // XMLReader
        {
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            saxFactory.setValidating(false);
            SAXParser parser = saxFactory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();
            //XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            TranscoderInput ti = new TranscoderInput(xmlReader);
            ti.setURI(TEST_URI);
            t.transcode(ti, out);
            assertTrue(t.passed);
        }
        
        // Input Stream
        {
            URL uri = new URL(TEST_URI);
            InputStream is = uri.openStream();
            TranscoderInput ti = new TranscoderInput(is);
            ti.setURI(TEST_URI);
            t = new TestTranscoder();
            t.transcode(ti, out);
            assertTrue(t.passed);
        }

        // Reader
        {
            URL uri = new URL(TEST_URI);
            InputStream is = uri.openStream();
            Reader r = new InputStreamReader(is);
            TranscoderInput ti = new TranscoderInput(r);
            ti.setURI(TEST_URI);
            t = new TestTranscoder();
            t.transcode(ti, out);
            assertTrue(t.passed);
        }
        // Document
        {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
            Document doc = f.createDocument(TEST_URI);        
            TranscoderInput ti = new TranscoderInput(doc);
            ti.setURI(TEST_URI);
            t = new TestTranscoder();
            t.transcode(ti, out);
            assertTrue(t.passed);
        }

        // Generic Document
        {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            DOMImplementation impl = 
                GenericDOMImplementation.getDOMImplementation();
            SAXDocumentFactory f = new SAXDocumentFactory(impl, parser);
            Document doc = f.createDocument(TEST_URI);
            TranscoderInput ti = new TranscoderInput(doc);
            ti.setURI(TEST_URI);
            t = new TestTranscoder();
            t.transcode(ti, out);
            assertTrue(t.passed);
        }

        // URI only
        {
            TranscoderInput ti = new TranscoderInput(TEST_URI);
            t = new TestTranscoder();
            t.transcode(ti, out);
            assertTrue(t.passed);
        }
        
        return reportSuccess();
    }
