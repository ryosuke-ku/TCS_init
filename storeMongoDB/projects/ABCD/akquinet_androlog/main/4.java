    public static void configure(Properties configuration) {

        boolean activate = "true".equalsIgnoreCase(configuration
                .getProperty(Constants.ANDROLOG_ACTIVE));
        if (activate) {
            activateLogging();
        }

        boolean activate4Report = "true".equalsIgnoreCase(configuration
                .getProperty(Constants.ANDROLOG_REPORT_ACTIVE));
        if (activate4Report) {
            activateReporting();
        }

        detectWTFMethods();

        if (configuration.containsKey(Constants.ANDROLOG_DEFAULT_LEVEL)) {
            String level = configuration.getProperty(Constants.ANDROLOG_DEFAULT_LEVEL);
            defaultLogLevel = LogHelper.getLevel(level, defaultLogLevel);
        }

        if (configuration.containsKey(Constants.ANDROLOG_REPORT_DEFAULT_LEVEL)) {
            String level = configuration
                    .getProperty(Constants.ANDROLOG_REPORT_DEFAULT_LEVEL);
            defaultReportLevel = LogHelper.getLevel(level, defaultReportLevel);
        }

        @SuppressWarnings("unchecked")
        Enumeration<String> names = (Enumeration<String>) configuration
                .propertyNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (!name.startsWith(Constants.ANDROLOG_PREFIX)) {
                String level = configuration.getProperty(name);
                int log = LogHelper.getLevel(level, defaultLogLevel);
                logLevels.put(name, log);
            }
        }

        if (useWTF) {
            // Check if androlog configuration does not override this.
            if (configuration.containsKey(Constants.ANDROLOG_DELEGATE_WTF)) {
                String v = configuration.getProperty(Constants.ANDROLOG_DELEGATE_WTF);
                // If androlog.delegate.wtf is set to true, we really call
                // Log.wtf which
                // may terminate the process.
                useWTF = "true".equals(v.toLowerCase());
                // In other cases, androlog does log a message in the Constants.ASSERT
                // level.
            }
        }

        // Do we need to store the log entries for Reports ?
        enableLogEntryCollection = false;
        if (context != null
                && configuration.containsKey(Constants.ANDROLOG_REPORT_REPORTERS)) {
            // We enable the collection only if we have reporters AND a valid
            // context
            String s = configuration.getProperty(Constants.ANDROLOG_REPORT_REPORTERS);
            String[] senders = s.split(",");
            for (String sender : senders) {
                String cn = sender.trim();
                Reporter reporter = InstanceFactory.newReporter(cn);
                if (reporter != null) {
                    reporter.configure(configuration);
                    reporters.add(reporter);
                }
            }

            // Configure the UncaughtExceptionHandler
            if (configuration.containsKey(Constants.ANDROLOG_REPORT_EXCEPTION_HANDLER)
                    && "false".equals(configuration.getProperty(Constants.ANDROLOG_REPORT_EXCEPTION_HANDLER))) {
                exceptionHandlerActivated = false;
            }

            if (configuration.containsKey(Constants.ANDROLOG_REPORT_EXCEPTION_HANDLER_PROPAGATION)
                    && "false".equals(configuration.getProperty(Constants.ANDROLOG_REPORT_EXCEPTION_HANDLER_PROPAGATION))) {
                exceptionHandlerPropagation = false;
            }

            // Define an default error handler, reporting the error.
            if (exceptionHandlerActivated) {
                originalHandler = Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread arg0, Throwable arg1) {
                        report("Uncaught Exception", arg1);
                        // If there is a original handler, propagate the exception.
                        if (exceptionHandlerPropagation  && originalHandler != null) {
                            originalHandler.uncaughtException(arg0, arg1);
                        }
                    }
                });
            }

            if (configuration.containsKey(Constants.ANDROLOG_REPORT_TRIGGER_LEVEL)) {
                String l = configuration.getProperty(Constants.ANDROLOG_REPORT_TRIGGER_LEVEL);
                reportTriggerLevel = LogHelper.getLevel(l, defaultReportLevel);
            } else {
                reportTriggerLevel = Constants.ASSERT;
            }
            
            if (configuration.containsKey(Constants.ANDROLOG_REPORT_FACTORY)) {
                s = configuration.getProperty(Constants.ANDROLOG_REPORT_FACTORY);
                reportFactory = InstanceFactory.newReportFactory(s);
            }
            
            if ("true".equalsIgnoreCase(configuration
                .getProperty(Constants.ANDROLOG_REPORT_ADD_TIMESTAMP))) {
                addTimestampToReportLogs = true;
            }
            
            enableLogEntryCollection = true;
        }

        if (enableLogEntryCollection) {
            if (configuration.containsKey(Constants.ANDROLOG_REPORT_LOG_ITEMS)) {
                String p = configuration.getProperty(Constants.ANDROLOG_REPORT_LOG_ITEMS);
                maxOfEntriesInReports = Integer.parseInt(p);
            } else {
                maxOfEntriesInReports = 25; // Default
            }
            entries = new ArrayList<String>(maxOfEntriesInReports);
        }

    }
