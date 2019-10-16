  public void withoutPackage() {
    String CLASSNAME = "C";
    ClassName className = ClassName.fromQualifiedClassName(CLASSNAME);
    Assert.assertNotNull(className);
    Assert.assertEquals(CLASSNAME, className.getQualifiedClassName());
    Assert.assertEquals(CLASSNAME, className.toString());
    Assert.assertEquals("", className.getPackageName());
    Assert.assertEquals("C", className.getClassName());
    Assert.assertEquals("C.class", className.asClassFileName());
    Assert.assertEquals("C.java", className.asSourceFileName());
  }
