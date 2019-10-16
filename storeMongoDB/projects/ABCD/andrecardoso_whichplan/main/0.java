	public int sumDurations() {
		int durationSum = 0;
		for (Call call : calls) {
			durationSum += call.getDuration();
		}
		return durationSum;
	}
