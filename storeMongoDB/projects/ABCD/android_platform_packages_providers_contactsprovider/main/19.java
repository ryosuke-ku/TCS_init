    public void onAccountsUpdated(Account[] accounts) {
        scheduleBackgroundTask(BACKGROUND_TASK_UPDATE_ACCOUNTS);
    }
