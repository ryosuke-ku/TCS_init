  private boolean checkExpected(SortedList sl, int[] ids) { 
    if (Arrays.equals(sl.toNativeArray(), ids)) {
      return true;
    }
    return false;  
  }
