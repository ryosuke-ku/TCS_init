    public void requestForModelById() {
        final ModelRequestBuilder modelRequestBuilder = new ModelRequestBuilder();
        final UrlRequest urlRequest = modelRequestBuilder.requestForModelById(123, new HashMap<String, String>());
        Assert.assertEquals(urlRequest.getUrl(),
                "https://api.content.market.yandex.ru/v1/model/123.json?geo_id=0");
    }
