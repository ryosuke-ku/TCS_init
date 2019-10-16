  public int getSerializedSizeEstimate() {
    int size = 2 * (source.length() + streamName.length() + dataType.length() 
        + debuggingInfo.length()); // length of strings (pessimistic)
    size += data.length + 4;
    if (recordEndOffsets == null)
      size += 8;
    else
      size += 4 * (recordEndOffsets.length + 1); // +1 for length of array
    size += 8; // uuid
    return size;
  }
