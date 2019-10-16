    public static long dateToMilliseconds(int day, int hour, int min, int sec) {
        return (day * 24 * 60 * 60 * 1000) + (hour * 60 * 60 * 1000) + (min * 60 * 1000) + (sec * 1000);
    }
