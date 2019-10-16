    public String[] getTypes() {
        List<String> list = new ArrayList<String>();
        for (Element type : getExtensions(FeaturesHelper.TYPE)) {
            String value = type.getText();
            if (value != null) {
                value = value.trim();
                try {
                    list.add(new MimeType(value).toString());
                } catch (MimeTypeParseException e) {
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
