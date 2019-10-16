  public static Fun norm(
      final double fromAt,
      final double intoAt,
      final double fromVal,
      final double intoVal,
      final Fun fun
  ) {
    return new Fun() {
      @Override
      public double apply(double at) {
        double atNorm = (at - fromAt) / (intoAt - fromAt);
        double resNorm = fun.apply(atNorm);
        double res = fromVal + resNorm * (intoVal - fromVal);
        return res;
      }
    };
  }
