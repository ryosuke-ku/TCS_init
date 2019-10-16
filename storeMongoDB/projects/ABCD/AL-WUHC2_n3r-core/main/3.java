    public static <T> T xmlToBean(String xml, Class<T> clazz, Object typeMapping) {
        return (T) new RUnmarshaller().setTypeMapping(typeMapping).unmarshal(from(xml, false), clazz);
    }
