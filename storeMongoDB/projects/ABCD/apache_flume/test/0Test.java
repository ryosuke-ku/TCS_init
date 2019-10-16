  public void shouldReturnNextElement() {
    assertEquals("test1", fixture.get());
    assertEquals("test2", fixture.get());
    assertEquals("test1", fixture.get());
    assertEquals("test2", fixture.get());
    assertEquals("test1", fixture.get());
  }
sertEquals(String.valueOf(FIXED_TIME_MILLIS),
            timestampedEvent.getHeaders().get("timestamp"));
  }
