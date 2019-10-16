    protected Map<String, Object> getElementProperties(final INodeType nodeType, final IMappedStringData data,
            final boolean addNonMappedHeaders) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Property< ? >> properties = headers.get(nodeType);
        if (properties == null) {
            properties = new HashMap<String, AbstractSynonymsSaver.Property< ? >>();

            headers.put(nodeType, properties);
        }

        for (Entry<String, String> dataEntry : data.entrySet()) {
            if (!StringUtils.isEmpty(dataEntry.getValue())) {
                String headerName = dataEntry.getKey();

                Property< ? > property = properties.get(headerName);
                if (property == null) {
                    property = createProperty(nodeType, headerName, addNonMappedHeaders);

                    properties.put(headerName, property);
                }

                Object value = property.parse(data);
                if (value != null) {
                    result.put(property.getPropertyName(), value);
                }
            }
        }

        return result;
    }
