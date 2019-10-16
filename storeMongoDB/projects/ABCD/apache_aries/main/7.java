  public AriesApplication createApplication(URL url) throws ManagementException {
    OutputStream os = null;
    AriesApplication app = null;
    try { 
      File tempFile = _localPlatform.getTemporaryFile();
      InputStream is = url.openStream();
      os = new FileOutputStream (tempFile);
      IOUtils.copy(is, os);
      IDirectory downloadedSource = FileSystem.getFSRoot(tempFile);
      app = createApplication (downloadedSource);
    } catch (IOException iox) {
      throw new ManagementException (iox);
    }
      finally { 
      IOUtils.close(os);
    }
    return app;
  }
