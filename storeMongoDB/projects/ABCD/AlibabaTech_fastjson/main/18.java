    public static final List<Object> parseArray(String text, Type[] types) {
        if (text == null) {
            return null;
        }

        List<Object> list;

        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.getGlobalInstance());
        Object[] objectArray = parser.parseArray(types);
        if (objectArray == null) {
            list = null;
        } else {
            list = Arrays.asList(objectArray);
        }

        handleResovleTask(parser, list);

        parser.close();

        return list;
    }
