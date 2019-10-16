	public boolean logout() {
		log.info("logout current user");
		session.invalidate();

		return true;

	}
