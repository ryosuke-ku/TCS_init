  public void testByteStreaming() throws IOException {
    for (int size = 0; size < 1024; size++) {
      for (byte def = -1; def < 2; def++) {
        for (int seed = 1001; seed < 1013; seed++) {
          StoreByte store = new StoreByte(size, def);
          Random rand = new Random(seed);
          for (int pos = 0; pos < size; pos++) {
            if (rand.nextBoolean()) {
              store.set(pos, (byte) rand.nextInt());
            }
          }

          final byte[] bytes = ByteStreams.toByteArray(store.createStream());
          final StoreByte storeStar = new StoreByte();
          storeStar.fromStream(new ByteArrayInputStream(bytes));

          final int storeSize = store.size();
          final int starSize = storeStar.size();
          if (storeSize != starSize) {
            throw new IllegalStateException(
                "store should have size = " + storeSize+ " but has size " + starSize
            );
          }
          for (int pos = 0; pos < size; pos++) {
            final byte orig = store.get(pos, (byte) 0);
            final byte star = storeStar.get(pos, (byte) 0);
            if (star != orig) {
              throw new IllegalStateException(
                  "pos = " + pos + " should be " + orig + " but is " + star
              );
            }
          }
        }
      }
    }

    System.out.println("byte-based streaming MAY work");
  }
