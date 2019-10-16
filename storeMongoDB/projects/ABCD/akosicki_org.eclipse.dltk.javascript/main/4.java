			public Iterator<Member> iterator() {
				return new IgnoreDuplicateMemberIterator(ignoreMembers);
			}
