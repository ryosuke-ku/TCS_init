	public static boolean currentFlexoVersionIsSmallerThanLastVersion(File projectDirectory) {
		File f = getVersionFile(projectDirectory);
		if (!f.exists()) {
			return false;
		} else {
			FlexoVersion v = getVersion(projectDirectory);
			// bidouille so that Version will accept 1.0.1RC1 as bigger than
			// 1.0.1beta
			if (logger.isLoggable(Level.FINE)) {
				logger.fine("Version is " + v);
			}
			return FlexoXMLMappings.latestRelease().isLesserThan(v);
		}
	}
