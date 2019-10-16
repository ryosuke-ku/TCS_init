      public double apply(double at) {
        double atNorm = (at - fromAt) / (intoAt - fromAt);
        double resNorm = fun.apply(atNorm);
        double res = fromVal + resNorm * (intoVal - fromVal);
        return res;
      }
