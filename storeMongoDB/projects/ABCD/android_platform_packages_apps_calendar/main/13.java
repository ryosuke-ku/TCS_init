    ContentValues getContentValuesFromModel(CalendarEventModel model) {
        String title = model.mTitle;
        boolean isAllDay = model.mAllDay;
        String rrule = model.mRrule;
        String timezone = model.mTimezone;
        if (timezone == null) {
            timezone = TimeZone.getDefault().getID();
        }
        Time startTime = new Time(timezone);
        Time endTime = new Time(timezone);

        startTime.set(model.mStart);
        endTime.set(model.mEnd);
        offsetStartTimeIfNecessary(startTime, endTime, rrule, model);

        ContentValues values = new ContentValues();

        long startMillis;
        long endMillis;
        long calendarId = model.mCalendarId;
        if (isAllDay) {
            // Reset start and end time, ensure at least 1 day duration, and set
            // the timezone to UTC, as required for all-day events.
            timezone = Time.TIMEZONE_UTC;
            startTime.hour = 0;
            startTime.minute = 0;
            startTime.second = 0;
            startTime.timezone = timezone;
            startMillis = startTime.normalize(true);

            endTime.hour = 0;
            endTime.minute = 0;
            endTime.second = 0;
            endTime.timezone = timezone;
            endMillis = endTime.normalize(true);
            if (endMillis < startMillis + DateUtils.DAY_IN_MILLIS) {
                // EditEventView#fillModelFromUI() should treat this case, but we want to ensure
                // the condition anyway.
                endMillis = startMillis + DateUtils.DAY_IN_MILLIS;
            }
        } else {
            startMillis = startTime.toMillis(true);
            endMillis = endTime.toMillis(true);
        }

        values.put(Events.CALENDAR_ID, calendarId);
        values.put(Events.EVENT_TIMEZONE, timezone);
        values.put(Events.TITLE, title);
        values.put(Events.ALL_DAY, isAllDay ? 1 : 0);
        values.put(Events.DTSTART, startMillis);
        values.put(Events.RRULE, rrule);
        if (!TextUtils.isEmpty(rrule)) {
            addRecurrenceRule(values, model);
        } else {
            values.put(Events.DURATION, (String) null);
            values.put(Events.DTEND, endMillis);
        }
        if (model.mDescription != null) {
            values.put(Events.DESCRIPTION, model.mDescription.trim());
        } else {
            values.put(Events.DESCRIPTION, (String) null);
        }
        if (model.mLocation != null) {
            values.put(Events.EVENT_LOCATION, model.mLocation.trim());
        } else {
            values.put(Events.EVENT_LOCATION, (String) null);
        }
        values.put(Events.AVAILABILITY, model.mAvailability);
        values.put(Events.HAS_ATTENDEE_DATA, model.mHasAttendeeData ? 1 : 0);

        int accessLevel = model.mAccessLevel;
        if (accessLevel > 0) {
            // For now the array contains the values 0, 2, and 3. We add one to match.
            // Default (0), Private (2), Public (3)
            accessLevel++;
        }
        values.put(Events.ACCESS_LEVEL, accessLevel);
        values.put(Events.STATUS, model.mEventStatus);
        if (model.isEventColorInitialized()) {
            if (model.getEventColor() == model.getCalendarColor()) {
                values.put(Events.EVENT_COLOR_KEY, NO_EVENT_COLOR);
            } else {
                values.put(Events.EVENT_COLOR_KEY, model.getEventColorKey());
            }
        }
        return values;
    }
