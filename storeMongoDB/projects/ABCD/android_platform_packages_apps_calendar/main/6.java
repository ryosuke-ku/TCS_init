    public static boolean isSameEvent(CalendarEventModel model, CalendarEventModel originalModel) {
        if (originalModel == null) {
            return true;
        }

        if (model.mCalendarId != originalModel.mCalendarId) {
            return false;
        }
        if (model.mId != originalModel.mId) {
            return false;
        }

        return true;
    }
