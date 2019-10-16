  public void singlePackage() {
    String CLASSNAME = "net.ClasspathTask";
    ClassName className = ClassName.fromQualifiedClassName(CLASSNAME);
    Assert.assertNotNull(className);
    Assert.assertEquals(CLASSNAME, className.getQualifiedClassName());
    Assert.assertEquals(CLASSNAME, className.toString());
    Assert.assertEquals("net", className.getPackageName());
    Assert.assertEquals("ClasspathTask", className.getClassName());
    Assert.assertEquals("net/ClasspathTask.class", className.asClassFileName());
    Assert.assertEquals("net/ClasspathTask.java", className.asSourceFileName());
    Assert.assertEquals("net", className.getPackageAsDirectoryName());
  }
