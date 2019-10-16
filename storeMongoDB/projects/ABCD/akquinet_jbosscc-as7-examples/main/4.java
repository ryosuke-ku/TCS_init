	public boolean persistOrUpdate() {
		if (instance.getId() == null) {
			blogEntryDao.persist(instance);
		} else {
			instance = blogEntryDao.merge(instance);
		}
		id = instance.getId();
		return true;
	}
