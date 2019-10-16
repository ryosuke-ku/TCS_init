  public Stopwatch stop() {
    long tick = ticker.read();
    checkState(isRunning);
    isRunning = false;
    elapsedNanos += tick - startTick;
    return this;
  }
