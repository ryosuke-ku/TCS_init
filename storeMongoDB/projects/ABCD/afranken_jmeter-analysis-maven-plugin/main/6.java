    public void setToleranceDirection(String toleranceDirection) {
        this.toleranceDirection = ToleranceDirection.valueOf(toleranceDirection);
        updateMinMaxValue();
    }
