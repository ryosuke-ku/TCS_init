			public void process(BufferedReader in, PrintStream out) throws IOException {
				
				out.println("What is your name?");
				out.flush();
				
				String name = in.readLine();
			
				out.println("Your name is " + name);
				out.flush();
			}
