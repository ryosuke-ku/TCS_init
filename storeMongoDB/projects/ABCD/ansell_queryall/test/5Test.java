    public final void testOverwrite()
    {
        Assert.assertFalse(WebappConfig._TEST_URI_COLLECTION_PROPERTY.overwrite());
        Assert.assertFalse(WebappConfig._TEST_STRING_COLLECTION_PROPERTY.overwrite());
        Assert.assertFalse(WebappConfig.TITLE_PROPERTIES.overwrite());
        Assert.assertFalse(WebappConfig.COMMENT_PROPERTIES.overwrite());
        Assert.assertFalse(WebappConfig.IMAGE_PROPERTIES.overwrite());
        Assert.assertFalse(WebappConfig.ACTIVE_PROFILES.overwrite());
        
        Assert.assertTrue(WebappConfig._TEST_BOOLEAN_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig._TEST_FLOAT_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig._TEST_INT_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig._TEST_LONG_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig._TEST_STRING_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig._TEST_URI_PROPERTY.overwrite());
        Assert.assertTrue(WebappConfig.USER_AGENT.overwrite());
        Assert.assertTrue(WebappConfig.DEFAULT_SEPARATOR.overwrite());
        
    }
