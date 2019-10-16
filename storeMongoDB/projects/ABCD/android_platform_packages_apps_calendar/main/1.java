    public static LinkedHashSet<Rfc822Token> getAddressesFromList(String list,
            Rfc822Validator validator) {
        LinkedHashSet<Rfc822Token> addresses = new LinkedHashSet<Rfc822Token>();
        Rfc822Tokenizer.tokenize(list, addresses);
        if (validator == null) {
            return addresses;
        }

        // validate the emails, out of paranoia. they should already be
        // validated on input, but drop any invalid emails just to be safe.
        Iterator<Rfc822Token> addressIterator = addresses.iterator();
        while (addressIterator.hasNext()) {
            Rfc822Token address = addressIterator.next();
            if (!validator.isValid(address.getAddress())) {
                Log.v(TAG, "Dropping invalid attendee email address: " + address.getAddress());
                addressIterator.remove();
            }
        }
        return addresses;
    }
