	public static AMRSReportsCommonTaskLock getInstance() {
		if (instance == null)
			instance = new AMRSReportsCommonTaskLock();
		return instance;
	}
