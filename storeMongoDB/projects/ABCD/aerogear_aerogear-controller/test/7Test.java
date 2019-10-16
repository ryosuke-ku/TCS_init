    public void injectParamValuesWithProperties() {
        final String uri = "/cars/{id}?param1={firstname}?propertyName=propertyValue";
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 10);
        map.put("firstname", "Fletch");
        final String processedUri = RequestUtils.injectParamValues(uri, map);
        assertThat(processedUri).isEqualTo("/cars/10?param1=Fletch?propertyName=propertyValue");
    }
