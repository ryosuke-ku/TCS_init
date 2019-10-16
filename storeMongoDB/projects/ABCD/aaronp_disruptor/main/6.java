    public long getCount()
    {
        long count = 0L;

        for (int i = 0, size = counts.length; i < size; i++)
        {
            count += counts[i];
        }

        return count;
    }
