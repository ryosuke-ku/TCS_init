    private CheckResult getCheckResult(String key) {
        CheckResult result = null;

        // Check by request group
        if (null != ENVIRONMENT.getRequestGroups()) {
            for (RequestGroup requestGroup : ENVIRONMENT.getRequestGroups()) {
                if (key.equals(requestGroup.getName())) {
                    result = requestGroup.getCheckResult();
                    break;
                }
            }
        }

        // Check by default
        if (null == result) {
            result = ENVIRONMENT.getCheckResult();
        }

        return result;
    }
