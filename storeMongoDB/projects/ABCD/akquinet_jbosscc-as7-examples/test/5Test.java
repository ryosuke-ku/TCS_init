	public void testDelete() throws Exception {
		BlogEntry blogEntry = new BlogEntryTestdataBuilder(
				databaseRule.getEntityManager()).buildAndSave();

		EasyMock.expect(conversationMock.isTransient()).andReturn(false)
				.anyTimes();

		mockProvider.replayAll();
		// init instance
		blogEntryService.setId(blogEntry.getId());
		blogEntryService.getInstance();

		blogEntryService.delete();
		mockProvider.verifyAll();

		BlogEntry loadObject = databaseRule.getTransactionHelper().loadObject(
				BlogEntry.class, blogEntry.getId());

		Assert.assertNull(loadObject);

	}
