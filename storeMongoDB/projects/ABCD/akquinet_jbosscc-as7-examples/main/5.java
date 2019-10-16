	public boolean delete() {
		log.info("delete blog entry " + instance);
		blogEntryDao.remove(instance);

		end();
		return true;
	}
