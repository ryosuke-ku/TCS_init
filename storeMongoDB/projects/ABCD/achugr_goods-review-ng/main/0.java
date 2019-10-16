    public UrlRequest requestForModelById(final long id, final Map<String, String> parameters) {
        return build(parameters, ResourceType.MODEL.getResourceType(), String.valueOf(id));
    }
