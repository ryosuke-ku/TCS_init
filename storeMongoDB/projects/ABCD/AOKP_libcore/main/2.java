        public Thread[] threads() {
            int threadCount;
            while (true) {
                threadCount = threadGroup.enumerate(threads);
                if (threadCount == threads.length) {
                    resize();
                } else {
                    break;
                }
            }
            if (threadCount < lastThread) {
                // avoid retaining pointers to threads that have ended
                Arrays.fill(threads, threadCount, lastThread, null);
            }
            lastThread = threadCount;
            return threads;
        }
