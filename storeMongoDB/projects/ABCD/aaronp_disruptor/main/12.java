    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Histogram{");

        sb.append("min=").append(getMin()).append(", ");
        sb.append("max=").append(getMax()).append(", ");
        sb.append("mean=").append(getMean()).append(", ");
        sb.append("99%=").append(getTwoNinesUpperBound()).append(", ");
        sb.append("99.99%=").append(getFourNinesUpperBound()).append(", ");

        sb.append('[');
        for (int i = 0, size = counts.length; i < size; i++)
        {
            sb.append(upperBounds[i]).append('=').append(counts[i]).append(", ");
        }

        if (counts.length > 0)
        {
            sb.setLength(sb.length() - 2);
        }
        sb.append(']');

        sb.append('}');

        return sb.toString();
    }
