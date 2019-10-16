    public void testDrawerListenerCallbacksOnClosingNoAnimationViaAPI() {
        // Open the drawer so it becomes visible
        onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START, false));

        // Register a mock listener
        DrawerLayout.DrawerListener mockedListener = mock(DrawerLayout.DrawerListener.class);
        mDrawerLayout.addDrawerListener(mockedListener);

        // Close the drawer
        onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START, false));

        // We expect that our listener has not been notified that the drawer has been opened
        verify(mockedListener, never()).onDrawerOpened(any(View.class));
        // We expect that our listener has been notified that the drawer has been closed
        // with the reference to our drawer
        verify(mockedListener, times(1)).onDrawerClosed(mStartDrawer);

        verify(mockedListener, times(1)).onDrawerSlide(any(View.class), eq(0f));

        // Attempt to close the drawer again.
        onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START, false));

        // We expect that our listener has not been notified that the drawer has been opened
        verify(mockedListener, never()).onDrawerOpened(any(View.class));
        // We expect that our listener has not been notified again that the drawer has been closed
        verify(mockedListener, times(1)).onDrawerClosed(mStartDrawer);

        mDrawerLayout.removeDrawerListener(mockedListener);
    }
