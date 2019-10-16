  public RSSFeed load(String uri) throws RSSReaderException {
    final HttpGet httpget = new HttpGet(uri);

    InputStream feedStream = null;
    try {
      // Send GET request to URI
      final HttpResponse response = httpclient.execute(httpget);

      // Check if server response is valid
      final StatusLine status = response.getStatusLine();
      if (status.getStatusCode() != HttpStatus.SC_OK) {
        throw new RSSReaderException(status.getStatusCode(),
            status.getReasonPhrase());
      }

      // Extract content stream from HTTP response
      HttpEntity entity = response.getEntity();
      feedStream = entity.getContent();

      RSSFeed feed = parser.parse(feedStream);

      if (feed.getLink() == null) {
        feed.setLink(android.net.Uri.parse(uri));
      }

      return feed;
    } catch (ClientProtocolException e) {
      throw new RSSFault(e);
    } catch (IOException e) {
      throw new RSSFault(e);
    } finally {
      Resources.closeQuietly(feedStream);
    }
  }
