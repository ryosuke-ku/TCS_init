  public void shouldIncludeButtonAndTimesPressedInToString() {
    MouseClickInfo button = MouseClickInfo.rightButton();
    assertThat(button.toString()).contains("button=RIGHT_BUTTON").contains("times=1");
  }
