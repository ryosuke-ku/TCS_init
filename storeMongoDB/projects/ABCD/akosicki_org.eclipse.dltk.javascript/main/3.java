	public Iterable<Member> ignoreDuplicates(
			final Collection<String> ignoreMembers) {
		return new Iterable<Member>() {
			public Iterator<Member> iterator() {
				return new IgnoreDuplicateMemberIterator(ignoreMembers);
			}
		};
	}
