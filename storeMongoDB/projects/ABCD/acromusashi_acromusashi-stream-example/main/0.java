    public void open(Map stormConf, TopologyContext context, SpoutOutputCollector collector)
    {
        super.open(stormConf, context, collector);
        this.httpget = new HttpGet(this.targetUrl);
        this.client = new DefaultHttpClient();
    }
