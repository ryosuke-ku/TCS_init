    public void addMeasurement(String label, String value) {
        if (mPerfWriter != null)
            mPerfWriter.writeMeasurement(label, value);
    }
