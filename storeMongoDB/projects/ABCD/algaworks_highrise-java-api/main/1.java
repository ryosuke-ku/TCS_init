	public NoteManager getNoteManager() {
		return new NoteManager(this.webResource, this.authorization);
	}
