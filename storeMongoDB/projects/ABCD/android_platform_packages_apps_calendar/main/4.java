    void checkTimeDependentFields(CalendarEventModel originalModel, CalendarEventModel model,
            ContentValues values, int modifyWhich) {
        long oldBegin = model.mOriginalStart;
        long oldEnd = model.mOriginalEnd;
        boolean oldAllDay = originalModel.mAllDay;
        String oldRrule = originalModel.mRrule;
        String oldTimezone = originalModel.mTimezone;

        long newBegin = model.mStart;
        long newEnd = model.mEnd;
        boolean newAllDay = model.mAllDay;
        String newRrule = model.mRrule;
        String newTimezone = model.mTimezone;

        // If none of the time-dependent fields changed, then remove them.
        if (oldBegin == newBegin && oldEnd == newEnd && oldAllDay == newAllDay
                && TextUtils.equals(oldRrule, newRrule)
                && TextUtils.equals(oldTimezone, newTimezone)) {
            values.remove(Events.DTSTART);
            values.remove(Events.DTEND);
            values.remove(Events.DURATION);
            values.remove(Events.ALL_DAY);
            values.remove(Events.RRULE);
            values.remove(Events.EVENT_TIMEZONE);
            return;
        }

        if (TextUtils.isEmpty(oldRrule) || TextUtils.isEmpty(newRrule)) {
            return;
        }

        // If we are modifying all events then we need to set DTSTART to the
        // start time of the first event in the series, not the current
        // date and time. If the start time of the event was changed
        // (from, say, 3pm to 4pm), then we want to add the time difference
        // to the start time of the first event in the series (the DTSTART
        // value). If we are modifying one instance or all following instances,
        // then we leave the DTSTART field alone.
        if (modifyWhich == MODIFY_ALL) {
            long oldStartMillis = originalModel.mStart;
            if (oldBegin != newBegin) {
                // The user changed the start time of this event
                long offset = newBegin - oldBegin;
                oldStartMillis += offset;
            }
            if (newAllDay) {
                Time time = new Time(Time.TIMEZONE_UTC);
                time.set(oldStartMillis);
                time.hour = 0;
                time.minute = 0;
                time.second = 0;
                oldStartMillis = time.toMillis(false);
            }
            values.put(Events.DTSTART, oldStartMillis);
        }
    }
