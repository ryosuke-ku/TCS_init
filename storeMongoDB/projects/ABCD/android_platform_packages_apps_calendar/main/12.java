    public static void setModelFromCursor(CalendarEventModel model, Cursor cursor) {
        if (model == null || cursor == null || cursor.getCount() != 1) {
            Log.wtf(TAG, "Attempted to build non-existent model or from an incorrect query.");
            return;
        }

        model.clear();
        cursor.moveToFirst();

        model.mId = cursor.getInt(EVENT_INDEX_ID);
        model.mTitle = cursor.getString(EVENT_INDEX_TITLE);
        model.mDescription = cursor.getString(EVENT_INDEX_DESCRIPTION);
        model.mLocation = cursor.getString(EVENT_INDEX_EVENT_LOCATION);
        model.mAllDay = cursor.getInt(EVENT_INDEX_ALL_DAY) != 0;
        model.mHasAlarm = cursor.getInt(EVENT_INDEX_HAS_ALARM) != 0;
        model.mCalendarId = cursor.getInt(EVENT_INDEX_CALENDAR_ID);
        model.mStart = cursor.getLong(EVENT_INDEX_DTSTART);
        String tz = cursor.getString(EVENT_INDEX_TIMEZONE);
        if (!TextUtils.isEmpty(tz)) {
            model.mTimezone = tz;
        }
        String rRule = cursor.getString(EVENT_INDEX_RRULE);
        model.mRrule = rRule;
        model.mSyncId = cursor.getString(EVENT_INDEX_SYNC_ID);
        model.mAvailability = cursor.getInt(EVENT_INDEX_AVAILABILITY);
        int accessLevel = cursor.getInt(EVENT_INDEX_ACCESS_LEVEL);
        model.mOwnerAccount = cursor.getString(EVENT_INDEX_OWNER_ACCOUNT);
        model.mHasAttendeeData = cursor.getInt(EVENT_INDEX_HAS_ATTENDEE_DATA) != 0;
        model.mOriginalSyncId = cursor.getString(EVENT_INDEX_ORIGINAL_SYNC_ID);
        model.mOriginalId = cursor.getLong(EVENT_INDEX_ORIGINAL_ID);
        model.mOrganizer = cursor.getString(EVENT_INDEX_ORGANIZER);
        model.mIsOrganizer = model.mOwnerAccount.equalsIgnoreCase(model.mOrganizer);
        model.mGuestsCanModify = cursor.getInt(EVENT_INDEX_GUESTS_CAN_MODIFY) != 0;

        int rawEventColor;
        if (cursor.isNull(EVENT_INDEX_EVENT_COLOR)) {
            rawEventColor = cursor.getInt(EVENT_INDEX_CALENDAR_COLOR);
        } else {
            rawEventColor = cursor.getInt(EVENT_INDEX_EVENT_COLOR);
        }
        model.setEventColor(Utils.getDisplayColorFromColor(rawEventColor));

        if (accessLevel > 0) {
            // For now the array contains the values 0, 2, and 3. We subtract
            // one to make it easier to handle in code as 0,1,2.
            // Default (0), Private (1), Public (2)
            accessLevel--;
        }
        model.mAccessLevel = accessLevel;
        model.mEventStatus = cursor.getInt(EVENT_INDEX_EVENT_STATUS);

        boolean hasRRule = !TextUtils.isEmpty(rRule);

        // We expect only one of these, so ignore the other
        if (hasRRule) {
            model.mDuration = cursor.getString(EVENT_INDEX_DURATION);
        } else {
            model.mEnd = cursor.getLong(EVENT_INDEX_DTEND);
        }

        model.mModelUpdatedWithEventCursor = true;
    }
