    public static String beanToXml(Object obj, String rootName, boolean enableFeature) {
        XMLTag result = null;
        return formatXml(new RMarshaller().setEnableFeature(enableFeature).marshal(rootName, obj, result));
    }
