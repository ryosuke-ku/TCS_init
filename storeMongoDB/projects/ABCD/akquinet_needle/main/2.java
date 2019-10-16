    static Map<String, String> loadResourceAndDefault(final String name) {

        final Map<String, String> result = loadDefaults();
        try {
            final ResourceBundle customBundle = ResourceBundle.getBundle(name);

            for (final String key : customBundle.keySet()) {
                addKeyValuePair(result, key, customBundle.getString(key));
            }

            final URL url = NeedleConfiguration.class.getResource("/" + name + ".properties");
            LOG.info("loaded Needle config named {} from {}", name, url);
        }
        catch (final Exception e) {
            LOG.warn(e.getMessage());
        }

        return result;
    }
