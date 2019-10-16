  boolean equals(Object o) {
    if(o instanceof Priority) {
      Priority r = (Priority) o;
      return (this.level == r.level);
    } else {
      return false;
    }
  }
