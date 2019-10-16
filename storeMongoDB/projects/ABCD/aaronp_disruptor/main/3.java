    public boolean addObservation(final long value)
    {
        int low = 0;
        int high = upperBounds.length - 1;

        while (low < high)
        {
            int mid = low + ((high - low) >> 1);
            if (upperBounds[mid] < value)
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }

        if (value <= upperBounds[high])
        {
            counts[high]++;
            trackRange(value);

            return true;
        }

        return false;
    }
