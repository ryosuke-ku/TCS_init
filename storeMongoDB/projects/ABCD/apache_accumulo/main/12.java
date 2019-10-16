  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    hcb.append(maxMemory).append(maxLatency).append(maxWriteThreads).append(timeout).append(durability);
    return hcb.toHashCode();
  }
