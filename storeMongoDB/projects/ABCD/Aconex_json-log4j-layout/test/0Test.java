    public void validateBasicLogStructure() {
        LoggingEvent event = createDefaultLoggingEvent();
        String logOutput = jsonLayout.format(event);
        validateBasicLogOutput(logOutput, event);
    }
