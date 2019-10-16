	public void releaseLock_shouldReleaseALockIfTheLockingClassMatches() throws Exception {
		// ensure the common lock is not locked yet
		Boolean actual = AMRSReportsCommonTaskLock.getInstance().isLocked();
		assertThat(actual, is(Boolean.FALSE));

		// attempt to get a lock using the test class
		actual = AMRSReportsCommonTaskLock.getInstance().getLock(this.getClass());
		assertThat(actual, is(Boolean.TRUE));

		// attempt to release the lock as a different class
		actual = AMRSReportsCommonTaskLock.getInstance().releaseLock(String.class);
		assertThat(actual, is(Boolean.FALSE));

		// verify the lock is still in place
		actual = AMRSReportsCommonTaskLock.getInstance().isLocked();
		assertThat(actual, is(Boolean.TRUE));

		// attempt to release the lock as the correct class
		actual = AMRSReportsCommonTaskLock.getInstance().releaseLock(this.getClass());
		assertThat(actual, is(Boolean.TRUE));

		// verify the lock is removed
		actual = AMRSReportsCommonTaskLock.getInstance().isLocked();
		assertThat(actual, is(Boolean.FALSE));
	}
