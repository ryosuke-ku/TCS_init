    public double value(double x) {
        int index = Arrays.binarySearch(abscissa, x);
        double fx = 0;

        if (index < -1) {
            // "x" is between "abscissa[-index-2]" and "abscissa[-index-1]".
            fx = ordinate[-index-2];
        } else if (index >= 0) {
            // "x" is exactly "abscissa[index]".
            fx = ordinate[index];
        } else {
            // Otherwise, "x" is smaller than the first value in "abscissa"
            // (hence the returned value should be "ordinate[0]").
            fx = ordinate[0];
        }

        return fx;
    }
