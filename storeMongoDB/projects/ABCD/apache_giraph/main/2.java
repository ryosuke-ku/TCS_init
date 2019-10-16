  public static final ComputationDoneName fromName(String name) {
    checkNotNull(name, "name is null");
    checkArgument(name.endsWith(COMPUTATION_DONE_SUFFIX),
        "Name %s is not a valid ComputationDoneName", name);

    return new ComputationDoneName(
        Integer.parseInt(name.replace(COMPUTATION_DONE_SUFFIX, "")));
  }
