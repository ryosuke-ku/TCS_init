	public void stop(boolean forceIfNecessary) throws IOException {
		if (this.serverProcess != null) {

			String basedir = getServerProps().getProperty(BASEDIR_KEY);

			StringBuffer pathBuf = new StringBuffer(basedir);

			if (!basedir.endsWith(File.separator)) {
				pathBuf.append(File.separator);
			}

			String defaultsFilePath = getServerProps().getProperty(
					DEFAULTS_FILE_KEY);

			pathBuf.append("bin");
			pathBuf.append(File.separator);
			pathBuf.append("mysqladmin shutdown");

			System.out.println(pathBuf.toString());

			Process mysqladmin = Runtime.getRuntime().exec(pathBuf.toString());

			int exitStatus = -1;

			try {
				exitStatus = mysqladmin.waitFor();
			} catch (InterruptedException ie) {
				; // ignore
			}

			//
			// Terminate the process if mysqladmin couldn't
			// do it, and the user requested a force stop.
			//
			if (exitStatus != 0 && forceIfNecessary) {
				forceStop();
			}
		}
	}
