    private void assertHprofData(HprofData hprofData, boolean strict) throws Exception {
        List<ThreadEvent> threadHistory = hprofData.getThreadHistory();
        assertNotNull(threadHistory);
        Set<Integer> threadsSeen = new HashSet<Integer>();
        Set<Integer> threadsActive = new HashSet<Integer>();
        for (ThreadEvent event : threadHistory) {
            assertNotNull(event);
            assertNotNull(event.type);
            switch (event.type) {
                case START:
                    assertNotNull(event.threadName);
                    assertTrue(threadsActive.add(event.threadId));
                    assertTrue(threadsSeen.add(event.threadId));
                    break;
                case END:
                    assertEquals(-1, event.objectId);
                    assertNull(event.threadName);
                    assertNull(event.groupName);
                    assertNull(event.parentGroupName);
                    if (strict) {
                        assertTrue(threadsActive.remove(event.threadId));
                    }
                    break;
            }
        }

        Set<Sample> samples = hprofData.getSamples();
        assertNotNull(samples);
        for (Sample sample : samples) {
            assertNotNull(sample);
            assertTrue(sample.count > 0);
            assertNotNull(sample.stackTrace);
            assertTrue(sample.stackTrace.stackTraceId != -1);
            assertTrue(threadsSeen.contains(sample.stackTrace.getThreadId()));
            assertNotNull(sample.stackTrace.getStackFrames());
        }
    }
