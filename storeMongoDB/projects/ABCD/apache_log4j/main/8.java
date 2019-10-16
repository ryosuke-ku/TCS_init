  void l7dlog(Priority priority, String key,  Object[] params, Throwable t) {
    if(repository.isDisabled(priority.level)) {
      return;
    }
    if(priority.isGreaterOrEqual(this.getEffectiveLevel())) {
      String pattern = getResourceBundleString(key);
      String msg;
      if(pattern == null) {
        msg = key;
    } else {
        msg = java.text.MessageFormat.format(pattern, params);
    }
      forcedLog(FQCN, priority, msg, t);
    }
  }
