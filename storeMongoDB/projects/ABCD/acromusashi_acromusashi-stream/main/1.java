    public static TimeUnit getParentUnit(TimeUnit unit)
    {
        TimeUnit result = null;

        switch (unit)
        {
        case HOURS:
            result = TimeUnit.DAYS;
            break;
        case MINUTES:
            result = TimeUnit.HOURS;
            break;
        case SECONDS:
            result = TimeUnit.MINUTES;
            break;
        case MILLISECONDS:
            result = TimeUnit.SECONDS;
            break;
        case MICROSECONDS:
            result = TimeUnit.MILLISECONDS;
            break;
        case NANOSECONDS:
            result = TimeUnit.MICROSECONDS;
            break;
        default:
            break;
        }

        return result;
    }
