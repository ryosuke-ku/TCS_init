  private void add(long timestamp, long value) {

    if( maxSamplesCount == 0 ) {
      return;
    }

    // Disabling the compression if maxSamplesCount < 0
    if (maxSamplesCount > 0) {
      // check whether the maximum of samples is reached and reduce number of samples if necessary
      if( samples.size() >= maxSamplesCount ) {

        // compress
        halve();
        compression *= 2;
      }
    }

    // add current sample
    if( compression == 1 ) {

      // store samples
      samples.add(value);
      timestamps.add(timestamp);
    }
    else {

      // buffer samples for aggregation
      samplesBuffer.add(value);
      timestampsBuffer.add(timestamp);
      if( samplesBuffer.size() >= compression ) {

        // we have collected enough items
        addAggregated(samplesBuffer, timestampsBuffer);
        samplesBuffer.clear();
        timestampsBuffer.clear();
      }
    }
  }
