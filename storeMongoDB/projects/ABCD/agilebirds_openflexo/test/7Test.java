	public void testGetUserTypeNamed() {
		String[] nameMatchingCustomerUserType = new String[] { "customer_release", "CustOmer_release", "CUSTOMER", "customer", "CustoMer" };
		assertAllNamesMatch(nameMatchingCustomerUserType, UserType.CUSTOMER);
		String[] nameMatchingAnalystUserType = new String[] { "analyst_release", "AnalYst_release", "ANALYST", "analyst", "AnaLyst" };
		assertAllNamesMatch(nameMatchingAnalystUserType, UserType.ANALYST);
		String[] nameMatchingDeveloperUserType = new String[] { "developer_release", "DevelOpEr_release", "DEVELOPER", "developer",
				"DeVelOper" };
		assertAllNamesMatch(nameMatchingDeveloperUserType, UserType.DEVELOPER);
		String[] nameMatchingMaintainerUserType = new String[] { "maintainer_release", "MaiNtaiNer_release", "MAINTAINER", "maintainer",
				"MainTaiNer" };
		assertAllNamesMatch(nameMatchingMaintainerUserType, UserType.MAINTAINER);
		String[] nameMatchingNoUserType = new String[] { "truc_release", "Dev", "COUCOU", "machin", "string" };
		assertAllNamesMatch(nameMatchingNoUserType, UserType.MAINTAINER);
		assertEquals("user type name null must return maintainer.", UserType.MAINTAINER, UserType.getUserTypeNamed(null));
	}
