	public void testServerController() throws Exception {

		if (this.baseDir != null) {
			System.out.println("Starting server @ " + this.baseDir);

			ServerController controller = new ServerController(this.baseDir);
			System.out.println(controller.start());
			System.out.println("Hit enter to stop server....");
			System.in.read();
			controller.stop(true);

		}
	}
