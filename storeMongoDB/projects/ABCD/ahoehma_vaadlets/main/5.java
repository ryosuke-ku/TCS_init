  public void output(final Writer aWriter) {
    JAXBUtils.marshal(aWriter, new Vaadlets().withRootComponent(this.component));
  }
