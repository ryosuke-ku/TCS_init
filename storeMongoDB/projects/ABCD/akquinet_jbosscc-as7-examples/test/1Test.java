	public void testLogout() throws Exception {
		mockProvider.resetToStrict(httpSessionMock);
		httpSessionMock.invalidate();

		mockProvider.replayAll();

		Assert.assertTrue(authenticator.logout());
		mockProvider.verifyAll();
	}
