	private void assertUserTypeIs(UserType expected) {
		if (UserType.CUSTOMER == expected) {
			Assert.assertTrue("UserType.isCustomerRelease() must be true", UserType.isCustomerRelease());
		} else {
			Assert.assertFalse("UserType.isCustomerRelease() must be false", UserType.isCustomerRelease());
		}
		if (UserType.ANALYST == expected) {
			Assert.assertTrue("UserType.isAnalystRelease() must be true", UserType.isAnalystRelease());
		} else {
			Assert.assertFalse("UserType.isAnalystRelease() must be false", UserType.isAnalystRelease());
		}
		if (UserType.DEVELOPER == expected) {
			Assert.assertTrue("UserType.isDevelopperRelease() must be true", UserType.isDevelopperRelease());
		} else {
			Assert.assertFalse("UserType.isDevelopperRelease() must be false", UserType.isDevelopperRelease());
		}
		if (UserType.MAINTAINER == expected) {
			Assert.assertTrue("UserType.isMaintainerRelease() must be true", UserType.isMaintainerRelease());
		} else {
			Assert.assertFalse("UserType.isMaintainerRelease() must be false", UserType.isMaintainerRelease());
		}
	}
