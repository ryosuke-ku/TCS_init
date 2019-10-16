	public void testBuildingMainUrl() {
		DataSource source = DataSource.register("X", "Affymetrix")
		    .mainUrl("http://www.affymetrix.com")
		    .asDataSource();
		Assert.assertEquals("http://www.affymetrix.com", source.getMainUrl());
	}
