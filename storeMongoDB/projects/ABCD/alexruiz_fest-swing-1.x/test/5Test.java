  public void shouldCreateMiddleButtonClickOneTime() {
    MouseClickInfo button = MouseClickInfo.middleButton();
    assertThat(button.button()).isEqualTo(MIDDLE_BUTTON);
    assertThat(button.times()).isEqualTo(1);
  }
