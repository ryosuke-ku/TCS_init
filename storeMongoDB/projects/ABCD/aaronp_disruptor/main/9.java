    public BigDecimal getMean()
    {
        if (0L == getCount())
        {
            return BigDecimal.ZERO;
        }

        long lowerBound = counts[0] > 0L ? minValue : 0L;
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0, size = upperBounds.length; i < size; i++)
        {
            if (0L != counts[i])
            {
                long upperBound = Math.min(upperBounds[i], maxValue);
                long midPoint = lowerBound + ((upperBound - lowerBound) / 2L);

                BigDecimal intervalTotal = new BigDecimal(midPoint).multiply(new BigDecimal(counts[i]));
                total = total.add(intervalTotal);
            }

            lowerBound = Math.max(upperBounds[i] + 1L, minValue);
        }

        return total.divide(new BigDecimal(getCount()), 2, RoundingMode.HALF_UP);
    }
