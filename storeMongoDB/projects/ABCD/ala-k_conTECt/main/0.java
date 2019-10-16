    public void addAddress(final Address _address) {
        if ( null != _address ) {
            if ( ! addresses.contains(_address) ) {
                addresses.add(_address);
            }
        }
    }
