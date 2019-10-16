	public void testConstructors() {
		String comp_name = "org.apache.felix.ipojo.test.scenarios.component.Multiconstructor";
		PojoMetadata manip = getManipulationMetadataForComponent(comp_name);
		assertEquals(3, manip.getConstructors().length);
		assertNotNull(manip.getConstructor(new String[] {String.class.getName(), String.class.getName()}));
		assertNotNull(manip.getConstructor(new String[] {String.class.getName(), String.class.getName(), Integer.TYPE.getName()}));
		assertNotNull(manip.getConstructor(new String[] {String.class.getName(), Integer.TYPE.getName()}));
		assertNull(manip.getConstructor(new String[] {String.class.getName()}));
	}
