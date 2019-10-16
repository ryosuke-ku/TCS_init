    public static long parseEmailDateTimeToMillis(String date) throws ParseException {
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        if (date.length() <= 10) {
            cal.setTime(mAbbrevEmailDateTimeFormat.parse(date));
        } else if (date.length() <= 20) {
            cal.setTime(mEmailDateTimeFormat.parse(date));
        } else {
            cal.setTime(mEmailDateTimeFormatWithMillis.parse(date));
        }
        return cal.getTimeInMillis();
    }
