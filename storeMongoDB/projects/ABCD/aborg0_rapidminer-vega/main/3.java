    public final IOContainer run(IOContainer input, int logVerbosity, Map<String, String> macroMap, boolean storeOutput) throws OperatorException {
        // fetching process name for logging
        String name = null;
        if (getProcessLocation() != null) {
            name = getProcessLocation().toString();
        }

        int myVerbosity = rootOperator.getParameterAsInt(ProcessRootOperator.PARAMETER_LOGVERBOSITY);
        if (logVerbosity == LogService.UNKNOWN_LEVEL) {
            logVerbosity = LogService.OFF;
        }
        logVerbosity = Math.min(logVerbosity, myVerbosity);
        getLogger().setLevel(WrapperLoggingHandler.LEVELS[logVerbosity]);
        String logFilename = rootOperator.getParameter(ProcessRootOperator.PARAMETER_LOGFILE);
        Handler logHandler = null;
        if (logFilename != null) {
            try {
                logHandler = new FileHandler(logFilename);
                logHandler.setFormatter(new SimpleFormatter());
                logHandler.setLevel(Level.ALL);
                getLogger().config("Logging process to file " + logFilename);
            } catch (Exception e) {
                getLogger().warning("Cannot create log file '" + logFilename + "': " + e);
            }
        }
        if (logHandler != null) {
            getLogger().addHandler(logHandler);
        }

        setProcessState(PROCESS_STATE_RUNNING);
        prepareRun(logVerbosity);

        long start = System.currentTimeMillis();
        if (name != null)
            getLogger().info("Process " + name + " starts");
        else
            getLogger().info("Process starts");
        getLogger().fine("Process:" + Tools.getLineSeparator() + getRootOperator().createProcessTree(3));

        // load data as specified in process context
        int firstInput = 0;
        if (input != null) {
            firstInput = input.getIOObjects().length;
        }
        loadInitialData(firstInput);

        // macros
        applyContextMacros();
        if (macroMap != null) {
            for (Map.Entry<String, String> entry : macroMap.entrySet()) {
                getMacroHandler().addMacro(entry.getKey(), entry.getValue());
            }
        }
        rootOperator.processStarts();

        try {
            UsageStatistics.getInstance().count(this, OperatorStatisticsValue.EXECUTION);
            if (input != null) {
                rootOperator.deliverInput(Arrays.asList(input.getIOObjects()));
            }
            rootOperator.execute();
            if (storeOutput) {
                saveResults();
            }
            IOContainer result = rootOperator.getResults();
            long end = System.currentTimeMillis();

            getLogger().fine("Process:" + Tools.getLineSeparator() + getRootOperator().createProcessTree(3));
            if (name != null)
                getLogger().info("Process " + name + " finished successfully after " + Tools.formatDuration(end - start));
            else
                getLogger().info("Process finished successfully after " + Tools.formatDuration(end - start));

            return result;
        } catch (OperatorException e) {
            if (e instanceof ProcessStoppedException) {
                Operator op = getOperator(((ProcessStoppedException) e).getOperatorName());
                UsageStatistics.getInstance().count(op, OperatorStatisticsValue.STOPPED);
            } else {
                UsageStatistics.getInstance().count(getCurrentOperator(), OperatorStatisticsValue.FAILURE);
                if (e instanceof UserError) {
                    UsageStatistics.getInstance().count(((UserError) e).getOperator(), OperatorStatisticsValue.USER_ERROR);
                } else {
                    UsageStatistics.getInstance().count(getCurrentOperator(), OperatorStatisticsValue.OPERATOR_EXCEPTION);
                }
            }
            throw e;
        } finally {
            stop();
            tearDown();
            if (logHandler != null) {
                getLogger().removeHandler(logHandler);
                logHandler.close();
            }
        }
    }
