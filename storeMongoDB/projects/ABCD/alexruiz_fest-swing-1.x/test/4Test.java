  public void shouldCreateLeftButtonClickOneTime() {
    MouseClickInfo button = MouseClickInfo.leftButton();
    assertThat(button.button()).isEqualTo(LEFT_BUTTON);
    assertThat(button.times()).isEqualTo(1);
  }
