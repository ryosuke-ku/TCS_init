  public void get() throws RSSReaderException {
    final RSSFeed feed = reader.load(BBC_NEWS);

    assertEquals("BBC News - World", feed.getTitle());

    assertEquals(
        android.net.Uri.parse("http://www.bbc.co.uk/news/world/#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa"),
        feed.getLink());

    assertEquals(
        "The latest stories from the World section of the BBC News web site.",
        feed.getDescription());
  }
