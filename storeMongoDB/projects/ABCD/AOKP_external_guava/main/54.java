    public boolean apply(CharSequence t) {
      return pattern.matcher(t).find();
    }
