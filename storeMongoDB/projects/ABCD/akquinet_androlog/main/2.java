    public static void reset() {
        deactivateLogging();
        deactivateReporting();
        defaultLogLevel = Constants.INFO;
        defaultReportLevel = Constants.INFO;
        detectWTFMethods();
        logLevels.clear();
        maxOfEntriesInReports = 25;
        enableLogEntryCollection = false;
        entries = null;
        reporters.clear();
        reportTriggerLevel = Constants.ASSERT;
        Thread.setDefaultUncaughtExceptionHandler(originalHandler);
        originalHandler = null;
    }
