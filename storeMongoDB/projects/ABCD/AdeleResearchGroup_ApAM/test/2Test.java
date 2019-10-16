	public void obrmanInstanciationWhenBundleInstalledNotStarted_tct005() {
		waitForApam();

		Implementation implementation = waitForImplByName(null,
				"Obrman-Test-S3Impl");

		Assert.assertNotNull(
				"Obrman-Test-S3Impl cannot be resolved (cannot be found using obrman)",
				implementation);

		Instance instance = implementation.createInstance(null,
				Collections.<String, String> emptyMap());

		Assert.assertNotNull("Instance of Obrman-Test-S3Impl is null", instance);
		Bundle bundle = implementation.getApformComponent().getBundle();

		try {
			bundle.stop();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		implementation = waitForImplByName(null, "Obrman-Test-S3Impl");

		Assert.assertNotNull(
				"Obrman-Test-S3Impl cannot be resolved as bundle is not started",
				implementation);

		instance = implementation.createInstance(null,
				Collections.<String, String> emptyMap());

		Assert.assertNotNull("Instance of Obrman-Test-S3Impl is null", instance);

	}
