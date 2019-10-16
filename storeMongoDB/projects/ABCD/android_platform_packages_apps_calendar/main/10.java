    void addRecurrenceRule(ContentValues values, CalendarEventModel model) {
        String rrule = model.mRrule;

        values.put(Events.RRULE, rrule);
        long end = model.mEnd;
        long start = model.mStart;
        String duration = model.mDuration;

        boolean isAllDay = model.mAllDay;
        if (end >= start) {
            if (isAllDay) {
                // if it's all day compute the duration in days
                long days = (end - start + DateUtils.DAY_IN_MILLIS - 1)
                        / DateUtils.DAY_IN_MILLIS;
                duration = "P" + days + "D";
            } else {
                // otherwise compute the duration in seconds
                long seconds = (end - start) / DateUtils.SECOND_IN_MILLIS;
                duration = "P" + seconds + "S";
            }
        } else if (TextUtils.isEmpty(duration)) {

            // If no good duration info exists assume the default
            if (isAllDay) {
                duration = "P1D";
            } else {
                duration = "P3600S";
            }
        }
        // recurring events should have a duration and dtend set to null
        values.put(Events.DURATION, duration);
        values.put(Events.DTEND, (Long) null);
    }
