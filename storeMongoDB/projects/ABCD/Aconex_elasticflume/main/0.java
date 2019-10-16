    public void append(Event e) throws IOException {
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("timestamp", new Date(e.getTimestamp()))
                    .field("host", e.getHost())
                    .field("priority", e.getPriority().name());

            addBody(builder, e.getBody());

            addAttrs(builder, e.getAttrs());

            index(e, builder);
        } catch (Exception ex) {
            LOG.error("Error Processing event: {}", e.toString(), ex);
            eventErrorCount.incrementAndGet();
        }
    }
       if (argv.length > 3) {
            sink.setIndexType(argv[index++]);
        }
        if (argv.length > 4) {
            sink.setIndexPattern(argv[index++]);
        }
        return sink;
    }
