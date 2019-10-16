    public void setTelephone(final Telephone _telephone) {
        telephone = _telephone;
        if ( ! telephones.contains(_telephone) ) {
            telephones.add(_telephone);
        }
    }
