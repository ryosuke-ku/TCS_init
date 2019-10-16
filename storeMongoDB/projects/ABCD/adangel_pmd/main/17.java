    public void addExample(String example) {
        // TODO Meaningful override of examples is hard, because they are merely
        // a list of strings. How does one indicate override of a particular
        // value? Via index? Rule.setExample(int, String)? But the XML format
        // does not provide a means of overriding by index, not unless you took
        // the position in the XML file to indicate corresponding index to
        // override. But that means you have to override starting from index 0.
        // This would be so much easier if examples had to have names, like
        // properties.

        // Only override if different than current values.
        if (!contains(super.getExamples(), example)) {
            if (examples == null) {
                examples = new ArrayList<>(1);
            }
            // TODO Fix later. To keep example overrides from being unbounded,
            // we're only going to keep track of the last one.
            examples.clear();
            examples.add(example);
            super.addExample(example);
        }
    }
