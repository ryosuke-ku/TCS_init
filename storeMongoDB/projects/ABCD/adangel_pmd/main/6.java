    public InputStream getInputStream(ClassLoader classLoader) throws RuleSetNotFoundException {
        if (externalRuleSetReferenceId == null) {
            InputStream in = StringUtil.isEmpty(ruleSetFileName) ? null : ResourceLoader.loadResourceAsStream(
                    ruleSetFileName, classLoader);
            if (in == null) {
                throw new RuleSetNotFoundException("Can't find resource '" + ruleSetFileName + "' for rule '"
                        + ruleName + "'" + ".  Make sure the resource is a valid file or URL and is on the CLASSPATH. "
                        + "Here's the current classpath: " + System.getProperty("java.class.path"));
            }
            return in;
        } else {
            return externalRuleSetReferenceId.getInputStream(classLoader);
        }
    }
