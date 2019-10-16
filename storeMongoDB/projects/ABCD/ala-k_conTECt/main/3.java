    public void addNote(final Note _note) {
        if ( null != _note ) {
            if ( ! notes.contains(_note) ) {
                notes.add(_note);
            }
        }
    }
