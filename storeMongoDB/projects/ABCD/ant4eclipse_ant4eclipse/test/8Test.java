  public void equalObjects() {
    String CLASSNAME = "net.sf.ant4eclipse.ClasspathTask";
    ClassName className1 = ClassName.fromQualifiedClassName(CLASSNAME);
    ClassName className2 = ClassName.fromQualifiedClassName(CLASSNAME);
    Assert.assertNotSame(className1, className2);

    Assert.assertEquals(className1.hashCode(), className2.hashCode());

    Assert.assertTrue(className1.equals(className1));
    Assert.assertTrue(className2.equals(className2));
    Assert.assertTrue(className1.equals(className2));
    Assert.assertTrue(className2.equals(className1));

    Assert.assertFalse(className1.equals(null));
    Assert.assertFalse(className1.equals(new LinkedList<Object>()));
    Assert.assertFalse(className1.equals(ClassName.fromQualifiedClassName("com.wuetherich.Test")));
    Assert.assertFalse(className1.equals(ClassName.fromQualifiedClassName("net.sf.ant4eclipse.ClasspathTaskTest")));
    Assert.assertFalse(className1.equals(ClassName.fromQualifiedClassName("com.wuetherich.ClasspathTask")));
  }
