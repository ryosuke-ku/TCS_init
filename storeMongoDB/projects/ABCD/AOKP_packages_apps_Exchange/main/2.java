    /* package for testing */ void showNotificationForTest(String emailAddresses) {
        // Launch Calendar app when clicked.
        PendingIntent launchCalendarPendingIntent = PendingIntent.getActivity(mContext, 0,
                createLaunchCalendarIntent(), 0);

        String tickerText = mContext.getString(R.string.notification_exchange_calendar_added);
        Notification n = new Notification(R.drawable.stat_notify_calendar,
                tickerText, System.currentTimeMillis());
        n.setLatestEventInfo(mContext, tickerText, emailAddresses, launchCalendarPendingIntent);
        n.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager nm =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_ID_EXCHANGE_CALENDAR_ADDED, n);
    }
