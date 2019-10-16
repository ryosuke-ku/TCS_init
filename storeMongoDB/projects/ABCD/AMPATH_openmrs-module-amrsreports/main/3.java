	public Boolean releaseLock(Class lockingClass) {
		if (lastLockingClass == lockingClass) {
			log.info("Releasing lock held by " + lockingClass.getSimpleName());
			locked = false;
		} else if (lastLockingClass != null)
			log.warn("Lock release requested by " + lockingClass.getSimpleName() + ", but held by " + lastLockingClass.getSimpleName());
		else
			log.warn("Lock release requested by " + lockingClass.getSimpleName() + ", but held by unknown class");

		return !locked;
	}
