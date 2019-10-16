    public final void testGetUri()
    {
        final URI testUri1 = this.testValueFactory.createURI("http://test.example.org/", "_testIntProperty");
        
        final URI testUri2 =
                this.testValueFactory.createURI(WebappConfig._TEST_INT_PROPERTY.getNamespace(),
                        WebappConfig._TEST_INT_PROPERTY.getKey());
        
        Assert.assertEquals(testUri1, testUri2);
        Assert.assertEquals(testUri1.stringValue(), testUri2.stringValue());
        
        Assert.assertEquals(testUri1, WebappConfig._TEST_INT_PROPERTY.getUri());
        Assert.assertEquals(testUri2, WebappConfig._TEST_INT_PROPERTY.getUri());
    }
