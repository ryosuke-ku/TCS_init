    public void testDrawerLockUnlock() {
        assertEquals("Drawer is unlocked in initial state",
                DrawerLayout.LOCK_MODE_UNLOCKED, mDrawerLayout.getDrawerLockMode(mStartDrawer));

        // Lock the drawer open
        onView(withId(R.id.drawer_layout)).perform(
                setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, GravityCompat.START));
        // Check that it's locked open
        assertEquals("Drawer is now locked open",
                DrawerLayout.LOCK_MODE_LOCKED_OPEN, mDrawerLayout.getDrawerLockMode(mStartDrawer));
        // and also opened
        assertTrue("Drawer is also opened", mDrawerLayout.isDrawerOpen(mStartDrawer));

        // Unlock the drawer
        onView(withId(R.id.drawer_layout)).perform(
                setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, mStartDrawer));
        // Check that it's still opened
        assertTrue("Drawer is still opened", mDrawerLayout.isDrawerOpen(mStartDrawer));
        // Close the drawer
        onView(withId(R.id.drawer_layout)).perform(closeDrawer(mStartDrawer));
        // Check that the drawer is unlocked
        assertEquals("Start drawer is now unlocked",
                DrawerLayout.LOCK_MODE_UNLOCKED, mDrawerLayout.getDrawerLockMode(mStartDrawer));

        // Open the drawer and then clock it closed
        onView(withId(R.id.drawer_layout)).perform(openDrawer(mStartDrawer));
        onView(withId(R.id.drawer_layout)).perform(
                setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START));
        // Check that the drawer is locked close
        assertEquals("Drawer is now locked close",
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                mDrawerLayout.getDrawerLockMode(mStartDrawer));
        // and also closed
        assertFalse("Drawer is also closed", mDrawerLayout.isDrawerOpen(mStartDrawer));

        // Unlock the drawer
        onView(withId(R.id.drawer_layout)).perform(
                setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, mStartDrawer));
        // Check that it's still closed
        assertFalse("Drawer is still closed", mDrawerLayout.isDrawerOpen(mStartDrawer));
    }
