	public void add(Type type, Predicate<Member> predicate) {
		Assert.isNotNull(type);
		types.add(new QueueItem(type, predicate));
	}
