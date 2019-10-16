  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ClassName other = (ClassName) obj;
    return this._qualifiedName.equals(other._qualifiedName);
  }
