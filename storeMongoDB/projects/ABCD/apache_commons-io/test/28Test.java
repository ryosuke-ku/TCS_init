    public void testSymmetryOfLong() {

        final double[] tests = new double[] {34.345, -345.5645, 545.12, 10.043, 7.123456789123};
        for (final double test : tests) {

            // testing the real problem
            byte[] buffer = new byte[8];
            final long ln1 = Double.doubleToLongBits( test );
            EndianUtils.writeSwappedLong(buffer, 0, ln1);
            final long ln2 = EndianUtils.readSwappedLong(buffer, 0);
            assertEquals( ln1, ln2 );

            // testing the bug report
            buffer = new byte[8];
            EndianUtils.writeSwappedDouble(buffer, 0, test);
            final double val = EndianUtils.readSwappedDouble(buffer, 0);
            assertEquals( test, val, 0 );
        }
    }
