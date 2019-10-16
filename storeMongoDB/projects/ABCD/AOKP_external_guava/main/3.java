  public Stopwatch reset() {
    elapsedNanos = 0;
    isRunning = false;
    return this;
  }
