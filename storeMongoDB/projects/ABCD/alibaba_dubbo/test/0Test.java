    public void testInterpolateSequence() throws Exception {
        String regexp = ParseUtils.interpolate("1.0.[0-9]", new HashMap<String, String>());
        assertEquals("1.0.[0-9]", regexp.toString());
    }
        
        pl = new PageList<Object>(0, -100, -3, null);
        Assert.assertEquals(1, pl.getPageCount());
        
        pl = new PageList<Object>(0, 30, 100, null);
        Assert.assertEquals(4, pl.getPageCount());
    }
