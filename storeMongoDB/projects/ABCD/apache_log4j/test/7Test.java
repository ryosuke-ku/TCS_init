  public void testGetFooter() {
    assertEquals("</table>", createLayout().getFooter().substring(0, 8));
  }
