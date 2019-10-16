  public String toString(int significantDigits) {
    long nanos = elapsedNanos();

    TimeUnit unit = chooseUnit(nanos);
    double value = (double) nanos / NANOSECONDS.convert(1, unit);

    // Too bad this functionality is not exposed as a regular method call
    return String.format("%." + significantDigits + "g %s",
        value, abbreviate(unit));
  }
