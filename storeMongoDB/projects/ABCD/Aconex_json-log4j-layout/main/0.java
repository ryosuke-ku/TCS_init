    public String format(LoggingEvent event) {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonGenerator g = createJsonGenerator(stringWriter);
            g.writeStartObject();
            writeBasicFields(event, g);
            writeMDCValues(event, g);
            writeThrowableEvents(event, g);
            writeNDCValues(event, g);
            g.writeEndObject();
            g.close();
            stringWriter.append("\n");
            return stringWriter.toString();
        } catch (IOException e) {
            throw new JSONLayoutException(e);
        }
    }
