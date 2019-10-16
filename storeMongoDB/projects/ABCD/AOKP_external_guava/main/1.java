  public Stopwatch start() {
    checkState(!isRunning);
    isRunning = true;
    startTick = ticker.read();
    return this;
  }
