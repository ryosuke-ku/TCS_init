    public static IPNetwork[] splitByCount(final IPNetwork network, int n) {
        if(network == null) {
            return null;
        }
        
        if(n < split_min) {
            throw new IllegalArgumentException("Unable to split " + network
                    + " into " + n + " network(s): value is lower than "
                    + split_min);
        } else if(n > split_max) {
            throw new IllegalArgumentException("Unable to split " + network
                    + " into " + n + " network(s): value is higher than "
                    + split_max);
        } else if((n & 1) != 0) {
            throw new IllegalArgumentException("Unable to split " + network
                    + " into " + n + " network(s): value is not a valid power of 2");
        }
        
        int x = 1;

        while((1<<x) < n) {
            x++;
        }
        
        if(Math.pow(2D, (double) x) != (double) n) {
            throw new IllegalArgumentException("Unable to split " + network
                    + " into " + n + " network(s): value is not a valid power of 2");
        }
        
        return splitByPower(network, x);
    }
