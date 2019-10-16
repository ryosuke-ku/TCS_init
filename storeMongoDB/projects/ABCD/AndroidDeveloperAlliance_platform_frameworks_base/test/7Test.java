    private void doTestSetBacklightBrightness() {
        try {
            mPm.setBacklightBrightness(0);
            fail("setBacklights did not throw SecurityException as expected");
        } catch (SecurityException e) {
            // expected
        }
    }
