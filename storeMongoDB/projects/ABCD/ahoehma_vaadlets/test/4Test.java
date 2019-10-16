  public void testFluentApi() {
    VaadletsBuilder.build(
        new Button().withId("foobar").withCaption("Foobar").withStyleName(BaseTheme.BUTTON_LINK).withWidth("100px"))
        .getRoot();
  }
