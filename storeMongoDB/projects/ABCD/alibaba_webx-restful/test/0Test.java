    public void test_json() throws Exception {
        Assert.assertSame(MediaType.APPLICATION_XML_TYPE, MediaTypeProvider.getInstance().fromString(MediaType.APPLICATION_XML));
        Assert.assertSame(MediaType.APPLICATION_JSON_TYPE, MediaTypeProvider.getInstance().fromString(MediaType.APPLICATION_JSON));
    }
t(1));
        
        List<String> values = new ArrayList<String>();
        pattern.match("/orders/123/jobs", values);
        
        Assert.assertEquals("123", values.get(0));
        Assert.assertEquals("jobs", values.get(1));
        
        Map<String, String> map = new HashMap<String, String>();
        pattern.getTemplate().match("/orders/234/ljw", map);
        
        Assert.assertEquals("234", map.get("id"));
        Assert.assertEquals("ljw", map.get("name"));
    }
