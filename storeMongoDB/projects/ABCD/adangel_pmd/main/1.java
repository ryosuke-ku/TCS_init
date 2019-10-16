    public static List<RuleSetReferenceId> parse(String referenceString) {
        List<RuleSetReferenceId> references = new ArrayList<>();
        if (referenceString != null && referenceString.trim().length() > 0) {

            if (referenceString.indexOf(',') == -1) {
                references.add(new RuleSetReferenceId(referenceString));
            } else {
                for (String name : referenceString.split(",")) {
                    references.add(new RuleSetReferenceId(name.trim()));
                }
            }
        }
        return references;
    }
