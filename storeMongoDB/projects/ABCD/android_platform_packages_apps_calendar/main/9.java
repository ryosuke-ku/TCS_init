    static boolean isFirstEventInSeries(CalendarEventModel model,
            CalendarEventModel originalModel) {
        return model.mOriginalStart == originalModel.mStart;
    }
