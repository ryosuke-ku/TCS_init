	public void testSuper() {
		String comp_name = "org.apache.felix.ipojo.test.scenarios.component.Child";
		PojoMetadata manip = getManipulationMetadataForComponent(comp_name);
		assertEquals("org.apache.felix.ipojo.test.scenarios.component.Parent", manip.getSuperClass());
		assertEquals(1, manip.getConstructors().length);
	}
