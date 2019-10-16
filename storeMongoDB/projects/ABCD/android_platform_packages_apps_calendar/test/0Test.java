    public void testGetAppWidgetModel_AllDayEventLater() throws Exception {
        Context context = getContext();
        CalendarAppWidgetModel expected = new CalendarAppWidgetModel(getContext(), Time
                .getCurrentTimezone());
        MatrixCursor cursor = new MatrixCursor(CalendarAppWidgetService.EVENT_PROJECTION, 0);

        int i = 0;

        // Expected Output
        EventInfo eventInfo = new EventInfo();
        eventInfo.visibWhen = View.VISIBLE;
        eventInfo.visibWhere = View.VISIBLE;
        eventInfo.visibTitle = View.VISIBLE;
        eventInfo.when = Utils.formatDateRange(context, now + ONE_HOUR, now + TWO_HOURS,
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_ABBREV_ALL);
        eventInfo.where = location + i;
        eventInfo.title = title + i;
        expected.mEventInfos.add(eventInfo);
        cursor.addRow(getRow(0, now + ONE_HOUR, now + TWO_HOURS, title + i, location + i, 0));

        i++;
        // Set the start time to 5 days from now at midnight UTC.
        Time time = new Time();
        time.set(now);
        time.monthDay += 5;
        time.hour = 0;
        time.timezone = Time.TIMEZONE_UTC;
        long start = time.normalize(false);
        time.monthDay += 1;
        long end = time.normalize(false);

        eventInfo = new EventInfo();
        eventInfo.visibWhen = View.VISIBLE;
        eventInfo.visibWhere = View.VISIBLE;
        eventInfo.visibTitle = View.VISIBLE;
        eventInfo.when = DateUtils.formatDateTime(context, end,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        eventInfo.where = location + i;
        eventInfo.title = title + i;
        expected.mEventInfos.add(eventInfo);
        cursor.addRow(getRow(1, start, end, title + i, location + i, 0));

        // Test
        CalendarAppWidgetModel actual = CalendarAppWidgetService.CalendarFactory.buildAppWidgetModel(
                context, cursor, Time.getCurrentTimezone());

        assertEquals(expected.toString(), actual.toString());
    }
