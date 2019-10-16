	public boolean login() {
		User user = userDao.findByUsername(username);

		if (user != null && user.verifyPassword(password)) {
			identity.setLoggedIn(true);
			identity.setUser(user);
			return true;
		}

		return false;
	}
