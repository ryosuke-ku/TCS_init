    public void testAttachDb() {
        String newDb = "/sdcard/mydata.db";
        File f = new File(newDb);
        if (f.exists()) {
            f.delete();
        }
        assertFalse(f.exists());
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(newDb, null);
        db.execSQL("create table test1 (i int);");
        db.execSQL("insert into test1 values(1);");
        db.execSQL("insert into test1 values(11);");
        Cursor c = null;
        try {
            c = db.rawQuery("select * from test1", null);
            int count = c.getCount();
            Log.i(TAG, "count: " + count);
            assertEquals(2, count);
        } finally {
            c.close();
            db.close();
            c = null;
        }

        mDatabase.execSQL("attach database ? as newDb" , new String[]{newDb});
        Cursor c1 = null;
        try {
            c1 = mDatabase.rawQuery("select * from newDb.test1", null);
            assertEquals(2, c1.getCount());
        } catch (Exception e) {
            fail("unexpected exception: " + e.getMessage());
        } finally {
            if (c1 != null) {
                c1.close();
            }
        }
        List<Pair<String, String>> dbs = mDatabase.getAttachedDbs();
        for (Pair<String, String> p: dbs) {
            Log.i(TAG, "attached dbs: " + p.first + " : " + p.second);
        }
        assertEquals(2, dbs.size());
     }
