    public void serialize() {
      Snapshot snapshot = new Snapshot.Builder().usedHeapSize(42).build();
      StringWriter writer = new StringWriter();
      this.serializer.serialize(snapshot, writer);
      String serializedRepresentation = writer.getBuffer().toString();
      assertNotNull(serializedRepresentation);
      assertTrue(serializedRepresentation.contains("42"));
    }
