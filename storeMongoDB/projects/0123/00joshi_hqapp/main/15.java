    public synchronized void addDefaultExcludes() {
        Stream<String> s = Stream.of(getDefaultExcludes()).map(p -> p.replace('/',
                File.separatorChar).replace('\\', File.separatorChar));
        if (excludes != null) {
            s = Stream.concat(Stream.of(excludes), s);
        }
        excludes = s.toArray(String[]::new);
    }
