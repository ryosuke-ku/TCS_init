    public void testGetResponseContentTypeStarSlashStar()
    {
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/rdf+xml");
        
        Assert.assertEquals("application/rdf+xml", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        // If they mistakenly put in application/xml, the otherwise default, application/rdf+xml
        // comes through, but this is not by design
        // NOTE: This is ONLY a sanity check to make sure that something rational happens and the
        // unuseful fallback text/fake isn't used if they specify */*
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/xml");
        
        Assert.assertEquals("application/rdf+xml", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/rdf+n3");
        
        Assert.assertEquals("text/rdf+n3", QueryallContentNegotiator.getResponseContentType("*/*", "dummy-agent/1.0",
                this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/turtle");
        
        Assert.assertEquals("text/turtle", QueryallContentNegotiator.getResponseContentType("*/*", "dummy-agent/1.0",
                this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/html");
        
        Assert.assertEquals("text/html", QueryallContentNegotiator.getResponseContentType("*/*", "dummy-agent/1.0",
                this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/json");
        
        Assert.assertEquals("application/rdf+json", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/rdf+json");
        
        Assert.assertEquals("application/rdf+json", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/ld+json");
        
        Assert.assertEquals("application/ld+json", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/x-nquads");
        
        Assert.assertEquals("text/x-nquads", QueryallContentNegotiator.getResponseContentType("*/*", "dummy-agent/1.0",
                this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/x-trig");
        
        Assert.assertEquals("application/x-trig", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("application/trix");
        
        Assert.assertEquals("application/trix", QueryallContentNegotiator.getResponseContentType("*/*",
                "dummy-agent/1.0", this.testContentNegotiator, "text/fake"));
        
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/plain");
        
        Assert.assertEquals("text/plain", QueryallContentNegotiator.getResponseContentType("*/*", "dummy-agent/1.0",
                this.testContentNegotiator, "text/fake"));
        
    }
