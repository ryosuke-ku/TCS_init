    public static boolean saveReminders(ArrayList<ContentProviderOperation> ops, long eventId,
            ArrayList<ReminderEntry> reminders, ArrayList<ReminderEntry> originalReminders,
            boolean forceSave) {
        // If the reminders have not changed, then don't update the database
        if (reminders.equals(originalReminders) && !forceSave) {
            return false;
        }

        // Delete all the existing reminders for this event
        String where = Reminders.EVENT_ID + "=?";
        String[] args = new String[] {Long.toString(eventId)};
        ContentProviderOperation.Builder b = ContentProviderOperation
                .newDelete(Reminders.CONTENT_URI);
        b.withSelection(where, args);
        ops.add(b.build());

        ContentValues values = new ContentValues();
        int len = reminders.size();

        // Insert the new reminders, if any
        for (int i = 0; i < len; i++) {
            ReminderEntry re = reminders.get(i);

            values.clear();
            values.put(Reminders.MINUTES, re.getMinutes());
            values.put(Reminders.METHOD, re.getMethod());
            values.put(Reminders.EVENT_ID, eventId);
            b = ContentProviderOperation.newInsert(Reminders.CONTENT_URI).withValues(values);
            ops.add(b.build());
        }
        return true;
    }
