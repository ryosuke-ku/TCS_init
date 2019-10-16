  void log(String callerFQCN, Priority level, Object message, Throwable t) {
    if(repository.isDisabled(level.level)) {
      return;
    }
    if(level.isGreaterOrEqual(this.getEffectiveLevel())) {
      forcedLog(callerFQCN, level, message, t);
    }
  }
