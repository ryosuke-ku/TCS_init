	public void testHandlerHeader2() throws ParseException {
		String header = "handler { $name=\"wbp\" $classname=\"org.apache.felix.ipojo.handler.wbp.WhiteBoardPatternHandler\"" +
				" $namespace=\"org.apache.felix.ipojo.whiteboard\" manipulation { $super=\"org.apache.felix.ipojo.PrimitiveHandler\"" +
				" field { $name=\"m_managers\" $type=\"java.util.List\" }method { $name=\"$init\" }method { $arguments=" +
				"\"{org.apache.felix.ipojo.metadata.Element,java.util.Dictionary}\" $name=\"configure\" }method { $name=\"start\"" +
				" }method { $arguments=\"{int}\" $name=\"stateChanged\" }method { $name=\"stop\" }}}";

		// This method returns an iPOJO root element
		Element elem = ManifestMetadataParser.parseHeaderMetadata(header);
		Element element = elem.getElements("handler")[0];

		Assert.assertEquals("handler", element.getName());
		Assert.assertNull(element.getNameSpace());

		Assert.assertEquals("wbp", element.getAttribute("name"));
		Assert.assertEquals("org.apache.felix.ipojo.handler.wbp.WhiteBoardPatternHandler", element.getAttribute("classname"));
		Assert.assertEquals("org.apache.felix.ipojo.whiteboard", element.getAttribute("namespace"));

		// Check the manipulation element
		Element[] manip = element.getElements("manipulation");
		Assert.assertNotNull(manip[0]);

		Element[] methods = manip[0].getElements("method");
		Assert.assertEquals(5, methods.length);
	}
