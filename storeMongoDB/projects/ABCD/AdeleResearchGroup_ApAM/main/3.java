	public Bundle getBundle(Resource resource) {
		
		String bundleName = resource.getSymbolicName();
		for (Bundle bundle : m_context.getBundles()) {
			if (bundle.getSymbolicName() != null && bundle.getSymbolicName().equals(bundleName)) {
				return bundle;
			}
		}
		
		return null;
	}
