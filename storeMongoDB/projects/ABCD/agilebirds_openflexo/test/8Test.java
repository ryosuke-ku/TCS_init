	private void assertAllNamesMatch(String[] names, UserType expected) {
		for (String name : names) {
			assertEquals(name + " must match the UserType " + expected.getName(), expected, UserType.getUserTypeNamed(name));
		}
	}
