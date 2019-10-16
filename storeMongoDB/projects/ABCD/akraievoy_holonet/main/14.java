  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EdgeData)) return false;

    final EdgeDataSparse edgeData = (EdgeDataSparse) o;

    if (Double.compare(edgeData.defElem, defElem) != 0) return false;
    if (symmetric != edgeData.symmetric) return false;

    final int linkCount = edgeData.getNonDefCount();
    if (getNonDefCount() != linkCount) {
      return false;
    }

    final int size = leads.length;
    if (size != edgeData.getSize()) {
      return false;
    }

    final ElemIterator thisNDI = nonDefIterator();
    final ElemIterator thatNDI = edgeData.nonDefIterator();

    while (thisNDI.hasNext()) {
      if (!thatNDI.hasNext()) {
        throw new IllegalStateException(
            "same link count, but `that` iterator exhausted?"
        );
      }

      if (!Util.eq(thisNDI.next(), thatNDI.next())) {
        return false;
      }
    }
    if (thatNDI.hasNext()) {
      throw new IllegalStateException(
          "same link count, but `that` iterator NOT exhausted?"
      );
    }

    return true;
  }
