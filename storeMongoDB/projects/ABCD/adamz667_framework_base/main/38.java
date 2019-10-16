    public List<Pair<String, String>> getAttachedDbs() {
        if (!isOpen()) {
            return null;
        }
        ArrayList<Pair<String, String>> attachedDbs = new ArrayList<Pair<String, String>>();
        if (!mHasAttachedDbs) {
            // No attached databases.
            // There is a small window where attached databases exist but this flag is not set yet.
            // This can occur when this thread is in a race condition with another thread
            // that is executing the SQL statement: "attach database <blah> as <foo>"
            // If this thread is NOT ok with such a race condition (and thus possibly not receive
            // the entire list of attached databases), then the caller should ensure that no thread
            // is executing any SQL statements while a thread is calling this method.
            // Typically, this method is called when 'adb bugreport' is done or the caller wants to
            // collect stats on the database and all its attached databases.
            attachedDbs.add(new Pair<String, String>("main", mPath));
            return attachedDbs;
        }
        // has attached databases. query sqlite to get the list of attached databases.
        Cursor c = null;
        try {
            c = rawQuery("pragma database_list;", null);
            while (c.moveToNext()) {
                // sqlite returns a row for each database in the returned list of databases.
                //   in each row,
                //       1st column is the database name such as main, or the database
                //                              name specified on the "ATTACH" command
                //       2nd column is the database file path.
                attachedDbs.add(new Pair<String, String>(c.getString(1), c.getString(2)));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return attachedDbs;
    }
