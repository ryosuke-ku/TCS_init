    public String updatePastEvents(ArrayList<ContentProviderOperation> ops,
            CalendarEventModel originalModel, long endTimeMillis) {
        boolean origAllDay = originalModel.mAllDay;
        String origRrule = originalModel.mRrule;
        String newRrule = origRrule;

        EventRecurrence origRecurrence = new EventRecurrence();
        origRecurrence.parse(origRrule);

        // Get the start time of the first instance in the original recurrence.
        long startTimeMillis = originalModel.mStart;
        Time dtstart = new Time();
        dtstart.timezone = originalModel.mTimezone;
        dtstart.set(startTimeMillis);

        ContentValues updateValues = new ContentValues();

        if (origRecurrence.count > 0) {
            /*
             * Generate the full set of instances for this recurrence, from the first to the
             * one just before endTimeMillis.  The list should never be empty, because this method
             * should not be called for the first instance.  All we're really interested in is
             * the *number* of instances found.
             *
             * TODO: the model assumes RRULE and ignores RDATE, EXRULE, and EXDATE.  For the
             * current environment this is reasonable, but that may not hold in the future.
             *
             * TODO: if COUNT is 1, should we convert the event to non-recurring?  e.g. we
             * do an "edit this and all future events" on the 2nd instances.
             */
            RecurrenceSet recurSet = new RecurrenceSet(originalModel.mRrule, null, null, null);
            RecurrenceProcessor recurProc = new RecurrenceProcessor();
            long[] recurrences;
            try {
                recurrences = recurProc.expand(dtstart, recurSet, startTimeMillis, endTimeMillis);
            } catch (DateException de) {
                throw new RuntimeException(de);
            }

            if (recurrences.length == 0) {
                throw new RuntimeException("can't use this method on first instance");
            }

            EventRecurrence excepRecurrence = new EventRecurrence();
            excepRecurrence.parse(origRrule);  // TODO: add+use a copy constructor instead
            excepRecurrence.count -= recurrences.length;
            newRrule = excepRecurrence.toString();

            origRecurrence.count = recurrences.length;

        } else {
            // The "until" time must be in UTC time in order for Google calendar
            // to display it properly. For all-day events, the "until" time string
            // must include just the date field, and not the time field. The
            // repeating events repeat up to and including the "until" time.
            Time untilTime = new Time();
            untilTime.timezone = Time.TIMEZONE_UTC;

            // Subtract one second from the old begin time to get the new
            // "until" time.
            untilTime.set(endTimeMillis - 1000); // subtract one second (1000 millis)
            if (origAllDay) {
                untilTime.hour = 0;
                untilTime.minute = 0;
                untilTime.second = 0;
                untilTime.allDay = true;
                untilTime.normalize(false);

                // This should no longer be necessary -- DTSTART should already be in the correct
                // format for an all-day event.
                dtstart.hour = 0;
                dtstart.minute = 0;
                dtstart.second = 0;
                dtstart.allDay = true;
                dtstart.timezone = Time.TIMEZONE_UTC;
            }
            origRecurrence.until = untilTime.format2445();
        }

        updateValues.put(Events.RRULE, origRecurrence.toString());
        updateValues.put(Events.DTSTART, dtstart.normalize(true));
        ContentProviderOperation.Builder b =
                ContentProviderOperation.newUpdate(Uri.parse(originalModel.mUri))
                .withValues(updateValues);
        ops.add(b.build());

        return newRrule;
    }
