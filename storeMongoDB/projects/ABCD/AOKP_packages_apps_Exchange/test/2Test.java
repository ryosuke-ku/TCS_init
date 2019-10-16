    public void testShowNotification() {
        CalendarSyncEnabler enabler = new CalendarSyncEnabler(getContext());

        // We can't really check the result, but at least we can make sure it won't crash....
        enabler.showNotificationForTest("a@b.com");

        // Remove the notification.  Comment it out when you want to know how it looks like.
        // TODO If NotificationController supports this notification, we can just mock it out
        // and remove this code.
        ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE))
                .cancel(CalendarSyncEnabler.NOTIFICATION_ID_EXCHANGE_CALENDAR_ADDED);
    }
