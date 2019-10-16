    @Override public int hashCode() {
      // Pattern uses Object.hashCode, so we have to reach
      // inside to build a hashCode consistent with equals.

      return Objects.hashCode(pattern.pattern(), pattern.flags());
    }
