	public TaskManager getTaskManager() {
		return new TaskManager(this.webResource, this.authorization);
	}
