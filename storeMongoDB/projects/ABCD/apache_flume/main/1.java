  public String getIndexName(Event event) {
    TimestampedEvent timestampedEvent = new TimestampedEvent(event);
    long timestamp = timestampedEvent.getTimestamp();
    String realIndexPrefix = BucketPath.escapeString(indexPrefix, event.getHeaders());
    return new StringBuilder(realIndexPrefix).append('-')
      .append(fastDateFormat.format(timestamp)).toString();
  }
