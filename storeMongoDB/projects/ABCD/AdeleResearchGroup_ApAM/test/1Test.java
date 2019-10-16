	public void compositeWithMainImplemUsingOBR_tct035() {

		InstanceCreator creator = new InstanceCreator("compositeWithMainImplem_tct034");
		creator.start();
		try {
			creator.join(1000);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		auxListInstances();

		Assert.assertTrue(
				"Composite should be created when main implem in another bundle",
				creator.created != null);
	}
