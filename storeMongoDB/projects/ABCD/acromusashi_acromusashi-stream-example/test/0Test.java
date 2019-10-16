    public void setUp()
    {
        String url = "http://localhost:8080/message";
        this.target = new HttpGetSpout(url);
        // ‰Šú‰»ˆ—À{
        this.target.open(this.mockConfMap, this.mockContext, this.mockCollector);
        this.target.client = this.httpClient;
    }
