    private void setRequestGroupCheckResult(int thresholdRequestGroup) {
        List<RequestGroup> requestGroups = new ArrayList<RequestGroup>();
        RequestGroup requestGroup1 = new RequestGroup();
        requestGroup1.setName("PATTERN_NAME");
        requestGroup1.setPattern("main");
        requestGroup1.setCheckResult(getCheckResult(thresholdRequestGroup));
        requestGroups.add(requestGroup1);
        ENVIRONMENT.setRequestGroups(requestGroups);
        ENVIRONMENT.setCheckResult(getCheckResult(4));
    }
