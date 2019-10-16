	public void testBuildingType() {
		DataSource source = DataSource.register("X", "Affymetrix")
		    .type("probe")
		    .asDataSource();
		Assert.assertEquals("probe", source.getType());
		Assert.assertFalse(source.isMetabolite());
	}
