    public void testDrawerHeight() {
        // Open the drawer so it becomes visible
        onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START));

        final int drawerLayoutHeight = mDrawerLayout.getHeight();
        final int startDrawerHeight = mStartDrawer.getHeight();
        final int contentHeight = mContentView.getHeight();

        // On all devices the height of the drawer layout and the drawer should be identical.
        assertEquals("Drawer layout and drawer heights", drawerLayoutHeight, startDrawerHeight);

        if (Build.VERSION.SDK_INT < 21) {
            // On pre-L devices the content height should be the same as the drawer layout height.
            assertEquals("Drawer layout and content heights on pre-L",
                    drawerLayoutHeight, contentHeight);
        } else {
            // Our drawer layout is configured with android:fitsSystemWindows="true" which should be
            // respected on L+ devices to extend the drawer layout into the system status bar.
            // The start drawer is also configured with the same attribute so it should have the
            // same height as the drawer layout. The main content does not have that attribute
            // specified, so it should have its height reduced by the height of the system status
            // bar.

            // Get the system window top inset that was propagated to the top-level DrawerLayout
            // during its layout.
            int drawerTopInset = mDrawerLayout.getSystemWindowInsetTop();
            assertTrue("Drawer top inset is positive on L+", drawerTopInset > 0);
            assertEquals("Drawer layout and drawer heights on L+",
                    drawerLayoutHeight - drawerTopInset, contentHeight);
        }
    }
