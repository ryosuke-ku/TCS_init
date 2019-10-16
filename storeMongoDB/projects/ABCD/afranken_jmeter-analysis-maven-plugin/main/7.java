    public Boolean valid(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive. " + value);
        }
        // Any verification if threshold is not greater than 0
        if (threshold >= 0) {
            if(ToleranceDirection.EQUALS.equals(toleranceDirection)) {
                return value == threshold;
            } else {
                return value >= minValue && value <= maxValue;
            }
        }
        return null;
    }
