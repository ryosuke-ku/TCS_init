	protected void accepted(JTelnetSocket socket) {
		
		sockets.add(socket);
		
		//Create a new thread to handle the communication
		Thread t = new Thread(socket);
		t.start();
		
	}
