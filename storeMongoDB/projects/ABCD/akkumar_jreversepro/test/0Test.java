  public void testBasicType() {
    assertTrue("Test for Basic Type INT ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_INT)));
    assertTrue("Test for Basic Type Boolean ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_BOOLEAN)));
    assertTrue("Test for Basic Type Byte ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_BYTE)));
    assertTrue("Test for Basic Type Char ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_CHAR)));
    assertTrue("Test for Basic Type Short ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_SHORT)));
    assertTrue("Test for Basic Type Float ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_FLOAT)));
    assertTrue("Test for Basic Type Long ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_LONG)));
    assertTrue("Test for Basic Type DOUBLE ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_DOUBLE)));
    assertFalse("Test for Basic Type ARRAY ", TypeInferrer.isBasicType(String
        .valueOf(JVM_TYPE_ARRAY)));

  }
