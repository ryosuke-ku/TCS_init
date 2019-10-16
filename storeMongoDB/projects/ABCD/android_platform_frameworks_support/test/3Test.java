    public void testDrawerListenerCallbacksOnClosingViaAPI() {
        // Open the drawer so it becomes visible
        onView(withId(R.id.drawer_layout)).perform(openDrawer(GravityCompat.START));

        // Register a mock listener
        DrawerLayout.DrawerListener mockedListener = mock(DrawerLayout.DrawerListener.class);
        mDrawerLayout.addDrawerListener(mockedListener);

        // Close the drawer
        onView(withId(R.id.drawer_layout)).perform(closeDrawer(GravityCompat.START));

        // We expect that our listener has not been notified that the drawer has been opened
        verify(mockedListener, never()).onDrawerOpened(any(View.class));
        // We expect that our listener has been notified that the drawer has been closed
        // with the reference to our drawer
        verify(mockedListener, times(1)).onDrawerClosed(mStartDrawer);

        // We expect that our listener has been notified at least once on the drawer slide
        // event. We expect that all such callbacks pass the reference to our drawer as the first
        // parameter, and we capture the float slide values for further analysis
        ArgumentCaptor<Float> floatSlideCaptor = ArgumentCaptor.forClass(float.class);
        verify(mockedListener, atLeastOnce()).onDrawerSlide(eq(mStartDrawer),
                floatSlideCaptor.capture());
        // Now we verify that calls to onDrawerSlide "gave" us a decreasing sequence of values
        // in [0..1] range. Note that we don't have any expectation on how many times onDrawerSlide
        // is called since that depends on the hardware capabilities of the device and the current
        // load on the CPU / GPU.
        assertThat(floatSlideCaptor.getAllValues(), inRange(0.0f, 1.0f));
        assertThat(floatSlideCaptor.getAllValues(), inDescendingOrder());

        // We expect that our listener will be called with specific state changes
        InOrder inOrder = inOrder(mockedListener);
        inOrder.verify(mockedListener).onDrawerStateChanged(DrawerLayout.STATE_SETTLING);
        inOrder.verify(mockedListener).onDrawerStateChanged(DrawerLayout.STATE_IDLE);

        mDrawerLayout.removeDrawerListener(mockedListener);
    }
