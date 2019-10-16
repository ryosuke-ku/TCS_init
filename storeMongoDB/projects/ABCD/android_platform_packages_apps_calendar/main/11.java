    static void updateRecurrenceRule(int selection, CalendarEventModel model,
            int weekStart) {
        // Make sure we don't have any leftover data from the previous setting
        EventRecurrence eventRecurrence = new EventRecurrence();

        if (selection == DOES_NOT_REPEAT) {
            model.mRrule = null;
            return;
        } else if (selection == REPEATS_CUSTOM) {
            // Keep custom recurrence as before.
            return;
        } else if (selection == REPEATS_DAILY) {
            eventRecurrence.freq = EventRecurrence.DAILY;
        } else if (selection == REPEATS_EVERY_WEEKDAY) {
            eventRecurrence.freq = EventRecurrence.WEEKLY;
            int dayCount = 5;
            int[] byday = new int[dayCount];
            int[] bydayNum = new int[dayCount];

            byday[0] = EventRecurrence.MO;
            byday[1] = EventRecurrence.TU;
            byday[2] = EventRecurrence.WE;
            byday[3] = EventRecurrence.TH;
            byday[4] = EventRecurrence.FR;
            for (int day = 0; day < dayCount; day++) {
                bydayNum[day] = 0;
            }

            eventRecurrence.byday = byday;
            eventRecurrence.bydayNum = bydayNum;
            eventRecurrence.bydayCount = dayCount;
        } else if (selection == REPEATS_WEEKLY_ON_DAY) {
            eventRecurrence.freq = EventRecurrence.WEEKLY;
            int[] days = new int[1];
            int dayCount = 1;
            int[] dayNum = new int[dayCount];
            Time startTime = new Time(model.mTimezone);
            startTime.set(model.mStart);

            days[0] = EventRecurrence.timeDay2Day(startTime.weekDay);
            // not sure why this needs to be zero, but set it for now.
            dayNum[0] = 0;

            eventRecurrence.byday = days;
            eventRecurrence.bydayNum = dayNum;
            eventRecurrence.bydayCount = dayCount;
        } else if (selection == REPEATS_MONTHLY_ON_DAY) {
            eventRecurrence.freq = EventRecurrence.MONTHLY;
            eventRecurrence.bydayCount = 0;
            eventRecurrence.bymonthdayCount = 1;
            int[] bymonthday = new int[1];
            Time startTime = new Time(model.mTimezone);
            startTime.set(model.mStart);
            bymonthday[0] = startTime.monthDay;
            eventRecurrence.bymonthday = bymonthday;
        } else if (selection == REPEATS_MONTHLY_ON_DAY_COUNT) {
            eventRecurrence.freq = EventRecurrence.MONTHLY;
            eventRecurrence.bydayCount = 1;
            eventRecurrence.bymonthdayCount = 0;

            int[] byday = new int[1];
            int[] bydayNum = new int[1];
            Time startTime = new Time(model.mTimezone);
            startTime.set(model.mStart);
            // Compute the week number (for example, the "2nd" Monday)
            int dayCount = 1 + ((startTime.monthDay - 1) / 7);
            if (dayCount == 5) {
                dayCount = -1;
            }
            bydayNum[0] = dayCount;
            byday[0] = EventRecurrence.timeDay2Day(startTime.weekDay);
            eventRecurrence.byday = byday;
            eventRecurrence.bydayNum = bydayNum;
        } else if (selection == REPEATS_YEARLY) {
            eventRecurrence.freq = EventRecurrence.YEARLY;
        }

        // Set the week start day.
        eventRecurrence.wkst = EventRecurrence.calendarDay2Day(weekStart);
        model.mRrule = eventRecurrence.toString();
    }
