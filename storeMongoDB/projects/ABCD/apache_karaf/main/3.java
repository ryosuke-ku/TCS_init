    public void write(PrintWriter writer, Converter<T> converter) {
        writer.printf("%s%n", converter.toString(this));
        for (Node<T> child : getChildren()) {
            child.write(writer, converter);
        }
        writer.flush();
    }
