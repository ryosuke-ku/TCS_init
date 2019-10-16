  static Symbol root(Symbol... symbols) {
    return new Root(symbols);
  }
 != null) {
      return true;
    }

    Schema schema = f.schema();
    Type type = schema.getType();

    // If the type is null, any value is valid
    if (type == Type.NULL) {
      return true;
    }

    // If the type is a union that allows nulls, any value is valid
    if (type == Type.UNION) {
      for (Schema s : schema.getTypes()) {
        if (s.getType() == Type.NULL) {
          return true;
        }
      }
    }

    // The value is null but the type does not allow nulls
    return false;
  }
