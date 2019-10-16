	public PeopleManager getPeopleManager() {
		return new PeopleManager(this.webResource, this.authorization);
	}
