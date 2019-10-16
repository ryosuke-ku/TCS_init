    public void check(Map<String, AggregatedResponses> jmeterResults) throws MojoFailureException {
        boolean check = true;

        for (String key : jmeterResults.keySet()) {
            AggregatedResponses aggregatedResponses = jmeterResults.get(key);
            CheckResult checkResult = getCheckResult(key);

            // Check throughput
            check &= checkValue(checkResult.getThroughput(),
                    aggregatedResponses.getDuration().getSuccessPerSecond(),
                    key, "throughput");

            // Check errors
            double percentErrors = (((double) aggregatedResponses.getDuration().getErrorsCount()) /
                    (aggregatedResponses.getDuration().getErrorsCount() + aggregatedResponses.getDuration().getSuccessCount())) * 100;
            check &= checkValue(checkResult.getErrors(), percentErrors,
                    key, "errors");
        }

        if (!check) {
            throw new MojoFailureException("Check is incorrect.");
        }
    }
