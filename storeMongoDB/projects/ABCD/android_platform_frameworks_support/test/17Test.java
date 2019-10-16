    public void testDrawerOpenCloseNoAnimationWithRedundancyViaAPI() {
        assertFalse("Initial state", mDrawerLayout.isDrawerOpen(GravityCompat.START));

        for (int i = 0; i < 5; i++) {
            onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START, false));
            assertTrue("Opened drawer #" + i, mDrawerLayout.isDrawerOpen(GravityCompat.START));

            // Try opening the drawer when it's already opened
            onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START, false));
            assertTrue("Opened drawer is still opened #" + i,
                    mDrawerLayout.isDrawerOpen(GravityCompat.START));

            onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START, false));
            assertFalse("Closed drawer #" + i, mDrawerLayout.isDrawerOpen(GravityCompat.START));

            // Try closing the drawer when it's already closed
            onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START, false));
            assertFalse("Closed drawer is still closed #" + i,
                    mDrawerLayout.isDrawerOpen(GravityCompat.START));
        }
    }
