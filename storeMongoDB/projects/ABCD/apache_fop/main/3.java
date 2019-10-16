    private AbstractTriplet getTriplet(byte tripletId) {
        for (AbstractTriplet trip : triplets) {
            if (trip.getId() == tripletId) {
                return trip;
            }
        }
        return null;
    }
