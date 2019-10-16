    public UrlRequest requestForListOfModelsOfCategoryById(final long id, final Map<String, String> parameters) {
        return build(parameters, ResourceType.CATEGORY.getResourceType(), String.valueOf(id), Resource.MODELS.getName());
    }
