                @Override public void run() {
                    c1.requery();
                    assertEquals(1, c1.getCount());
                }
