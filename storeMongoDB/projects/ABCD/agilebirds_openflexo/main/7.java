	public static UserType getUserTypeNamed(String userTypeName) {
		if (MAINTAINER.getName().equalsIgnoreCase(userTypeName)) {
			return MAINTAINER;
		}
		if (ANALYST.getName().equalsIgnoreCase(userTypeName)) {
			return ANALYST;
		}
		if (DEVELOPER.getName().equalsIgnoreCase(userTypeName)) {
			return DEVELOPER;
		}
		if (CUSTOMER.getName().equalsIgnoreCase(userTypeName)) {
			return CUSTOMER;
		}
		if (SEMANTICS_USER.getName().equalsIgnoreCase(userTypeName)) {
			return SEMANTICS_USER;
		}
		if (SEMANTICS_PLUS_USER.getName().equalsIgnoreCase(userTypeName)) {
			return SEMANTICS_PLUS_USER;
		}
		if (LITE.getName().equalsIgnoreCase(userTypeName)) {
			return LITE;
		}
		if (MAINTAINER.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return MAINTAINER;
		}
		if (ANALYST.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return ANALYST;
		}
		if (DEVELOPER.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return DEVELOPER;
		}
		if (CUSTOMER.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return CUSTOMER;
		}
		if (SEMANTICS_USER.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return SEMANTICS_USER;
		}
		if (SEMANTICS_PLUS_USER.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return SEMANTICS_PLUS_USER;
		}
		if (LITE.getIdentifier().equalsIgnoreCase(userTypeName)) {
			return LITE;
		}
		return MAINTAINER;
	}
