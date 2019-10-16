    public void testGetContentNegotiatorAnythingElseWithPreferredTextPlain()
    {
        this.testContentNegotiator = QueryallContentNegotiator.getContentNegotiator("text/plain");
        
        // Test against the only standardised format so far, RDF/XML to verify that it is still
        // available when NTriples is preferred
        Assert.assertEquals("application/rdf+xml", this.testContentNegotiator.getBestMatch("application/rdf+xml")
                .getMediaType());
        
        // Verify the strategy for selecting NTriples if RDF/XML is also in the list, to discourage
        // use of the text/plain mime type, while still making it useful if they specifically choose
        // it
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("application/rdf+xml,text/plain")
                .getMediaType());
        
        // Test NTriples against other possibilities while it is not preferred to make sure it is
        // above all of the others as well
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/rdf+n3")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/n3").getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/rdf+n3")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/n3")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/turtle")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/turtle")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/x-turtle")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/html")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/html")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/xhtml+xml")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/json")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/rdf+json")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/ld+json")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/x-nquads")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,text/nquads")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/x-trig")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/trig")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain,application/trix")
                .getMediaType());
        
        Assert.assertEquals("text/plain", this.testContentNegotiator.getBestMatch("text/plain").getMediaType());
        
    }
