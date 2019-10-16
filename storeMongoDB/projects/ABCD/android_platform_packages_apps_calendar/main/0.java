        protected static CalendarAppWidgetModel buildAppWidgetModel(
                Context context, Cursor cursor, String timeZone) {
            CalendarAppWidgetModel model = new CalendarAppWidgetModel(context, timeZone);
            model.buildFromCursor(cursor, timeZone);
            return model;
        }
