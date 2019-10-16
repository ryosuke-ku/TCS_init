    public void nextTuple()
    {
        String response = null;

        try
        {
            response = this.client.execute(this.httpget, new BasicResponseHandler());
        }
        catch (IOException ex)
        {
            String logFormat = "Http get failed. Skip target get. : TargetUrl={0}";
            logger.warn(MessageFormat.format(logFormat, this.targetUrl), ex);
            return;
        }

        Header header = new Header();
        header.setMessageId(UUID.randomUUID().toString());
        header.setTimestamp(System.currentTimeMillis());
        header.setType("http");

        Message message = new Message();
        message.setHeader(header);
        message.setBody(response);

        getCollector().emit(new Values(message));

        try
        {
            TimeUnit.MILLISECONDS.sleep(this.interval);
        }
        catch (InterruptedException iex)
        {
            if (logger.isDebugEnabled() == true)
            {
                logger.debug("Occur interrupt. Ignore interrupt.", iex);
            }
        }
    }
