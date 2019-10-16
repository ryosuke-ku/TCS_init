    private void parcelAndUnparcel(Uri u) {
        Parcel p = Parcel.obtain();
        try {
            Uri.writeToParcel(p, u);
            p.setDataPosition(0);
            assertEquals(u, Uri.CREATOR.createFromParcel(p));

            p.setDataPosition(0);
            u = u.buildUpon().build();
            Uri.writeToParcel(p, u);
            p.setDataPosition(0);
            assertEquals(u, Uri.CREATOR.createFromParcel(p));
        }
        finally {
            p.recycle();
        }
    }
