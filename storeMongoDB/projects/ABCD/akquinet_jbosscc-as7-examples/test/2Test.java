	public void testLoginSuccess() throws Exception {
		String username = "username";
		User user = new UserTestdataBuilder().withUsername(username).build();
		EasyMock.expect(userDaoMock.findByUsername(username)).andReturn(user);

		mockProvider.replayAll();
		authenticator.setUsername(username);
		authenticator.setPassword("secret");
		boolean login = authenticator.login();
		Assert.assertTrue(login);
		Assert.assertTrue(identity.isLoggedIn());
		Assert.assertEquals(identity.getUser(), user);
		mockProvider.verifyAll();
	}
