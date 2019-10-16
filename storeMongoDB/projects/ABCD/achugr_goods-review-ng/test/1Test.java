    public void requestForListOfModelsOfCategoryByIdTest() {
        final CategoryRequestBuilder categoryRequestBuilder = new CategoryRequestBuilder();
        final Map<String, String> params = new HashMap<String, String>();
        params.put("vendor_id", "1");
        params.put("sort", "price");
        final UrlRequest urlRequest = categoryRequestBuilder.requestForListOfModelsOfCategoryById(123, params);
        Assert.assertEquals(urlRequest.getUrl(),
                "https://api.content.market.yandex.ru/v1/category/123/models.json?geo_id=0&sort=price&vendor_id=1");
    }
