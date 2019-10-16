    public String toString() {
        if (ruleSetFileName != null) {
            if (allRules) {
                return ruleSetFileName;
            } else {
                return ruleSetFileName + "/" + ruleName;
            }

        } else {
            if (allRules) {
                return "anonymous all Rule";
            } else {
                return ruleName;
            }
        }
    }
