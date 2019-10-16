    public synchronized ReportEvent getMetrics() {
        ReportEvent event = new ReportEvent("ElasticSearchSink");
        event.setLongMetric(NO_OF_FAILED_EVENTS, eventErrorCount.longValue());
        return event;
    }
