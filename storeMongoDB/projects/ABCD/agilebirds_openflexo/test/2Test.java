	public void testCurrentFlexoVersionIsSmallerThanLastVersionWhenThereIsNoVersionFile() {
		assertFalse("No version file in a prj must be considered as not smaller than last version. "
				+ "(probably need to be fixed, but this test is conservative.)",
				FlexoProjectUtil.currentFlexoVersionIsSmallerThanLastVersion(getDirectoryPrjWitoutVersion()));
	}
