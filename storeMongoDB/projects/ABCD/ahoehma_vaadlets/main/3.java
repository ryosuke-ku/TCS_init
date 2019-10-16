  private void create(final com.mymita.vaadlets.core.Component aComponent) {
    component = aComponent;
    if (!(aComponent instanceof com.mymita.vaadlets.core.ComponentContainer)) {
      // XXX create a dummy component container
      final VerticalLayout dummy = new VerticalLayout();
      dummy.setSizeFull();
      root = new CustomComponent(dummy);
      dummy.addComponent(createVaadinComponent(aComponent));
    } else {
      root = createVaadinComponent(aComponent);
      createChildComponents(root, (com.mymita.vaadlets.core.ComponentContainer) aComponent);
    }
    for (final Entry<String, ClickListener> cl : clickListener.entrySet()) {
      final Component component = getComponent(cl.getKey());
      if (component instanceof Button) {
        ((Button) component).addListener(cl.getValue());
      }
    }
    for (final Entry<String, Listener> cl : componentListener.entrySet()) {
      final Component component = getComponent(cl.getKey());
      if (component != null) {
        component.addListener(cl.getValue());
      }
    }
  }
