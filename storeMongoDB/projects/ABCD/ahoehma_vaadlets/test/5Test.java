  public void testPersistVaadlets() throws FileNotFoundException, IOException {
    final File tempFile = File.createTempFile("foo", ".xml");
    VaadletsBuilder.build(
        new Vaadlets().withRootComponent(new VerticalLayout()
            .withHeight("100px")
            .withWidth("100px")
            .withStyleName("foobar")
            .withComponents(
                new Button().withCaption("hustensaft").withAlignment(Alignment.MIDDLE_CENTER).withExpandRatio(1f))))
        .output(new OutputStreamWriter(new FileOutputStream(tempFile)));
    System.out.println(Files.toString(tempFile, Charsets.UTF_8));
  }
