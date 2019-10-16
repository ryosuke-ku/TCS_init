    public static GregorianCalendar parseDateTimeToCalendar(String date) throws ParseException {
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        if (date.length() <= 8) {
            cal.setTime(mAbbrevDateTimeFormat.parse(date));
        } else {
            cal.setTime(mFullDateTimeFormat.parse(date));
        }
        return cal;
    }
