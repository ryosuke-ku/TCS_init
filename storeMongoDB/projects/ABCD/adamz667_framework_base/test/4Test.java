    private void runConnectionPoolTest(boolean useWal) throws Exception {
        int M = 3;
        StringBuilder[] buff = new StringBuilder[M];
        for (int i = 0; i < M; i++) {
            if (useWal) {
                // set up connection pool
                mDatabase.enableWriteAheadLogging();
                mDatabase.mConnectionPool.setMaxPoolSize(i + 1);
            } else {
                mDatabase.disableWriteAheadLogging();
            }
            mDatabase.execSQL("CREATE TABLE t1 (i int, j int);");
            mDatabase.execSQL("CREATE TABLE t2 (i int, j int);");
            mDatabase.beginTransaction();
            for (int k = 0; k < 5; k++) {
                mDatabase.execSQL("insert into t1 values(?,?);", new String[] {k+"", k+""});
                mDatabase.execSQL("insert into t2 values(?,?);", new String[] {k+"", k+""});
            }
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();

            // start a writer
            Writer w = new Writer(mDatabase);

            // initialize an array of counters to be passed to the readers
            Reader r1 = new Reader(mDatabase, "t1", w, 0);
            Reader r2 = new Reader(mDatabase, "t2", w, 1);
            w.start();
            r1.start();
            r2.start();

            // wait for all threads to die
            w.join();
            r1.join();
            r2.join();

            // print the stats
            int[][] counts = getCounts();
            buff[i] = new StringBuilder();
            buff[i].append("connpool-size = ");
            buff[i].append(i + 1);
            buff[i].append(", num xacts by writer = ");
            buff[i].append(getNumXacts());
            buff[i].append(", num-reads-in-xact/NOT-in-xact by reader1 = ");
            buff[i].append(counts[0][1] + "/" + counts[0][0]);
            buff[i].append(", by reader2 = ");
            buff[i].append(counts[1][1] + "/" + counts[1][0]);

            Log.i(TAG, "done testing for conn-pool-size of " + (i+1));

            dbTeardown();
            dbSetUp();
        }
        Log.i(TAG, "duration of test " + TIME_TO_RUN_WAL_TEST_FOR + " sec");
        for (int i = 0; i < M; i++) {
            Log.i(TAG, buff[i].toString());
        }
    }
