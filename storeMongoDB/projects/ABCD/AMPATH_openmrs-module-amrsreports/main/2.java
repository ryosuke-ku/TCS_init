	public Boolean getLock(Class lockingClass) {
		if (lockingClass == null) {
			log.warn("Could not grant lock to a null class");
			return false;
		}

		if (locked) {
			if (lastLockingClass != null)
				log.warn("Lock requested by " + lockingClass.getSimpleName() + ", but held by " + lastLockingClass.getSimpleName());
			else
				log.warn("Lock requested by " + lockingClass.getSimpleName() + ", but held by unknown class");
			return false;
		}

		lastLockingClass = lockingClass;
		locked = true;

		log.info("Lock granted to " + lockingClass.getSimpleName());

		return true;
	}
