	public Process start() throws IOException {
		if (this.serverProcess != null) {
			throw new IllegalArgumentException("Server already started");
		} else {
			this.serverProcess = Runtime.getRuntime().exec(getCommandLine());

			return this.serverProcess;
		}
	}
