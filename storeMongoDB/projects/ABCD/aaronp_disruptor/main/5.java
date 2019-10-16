    public void clear()
    {
        maxValue = 0L;
        minValue = Long.MAX_VALUE;

        for (int i = 0, size = counts.length; i < size; i++)
        {
            counts[i] = 0L;
        }
    }
