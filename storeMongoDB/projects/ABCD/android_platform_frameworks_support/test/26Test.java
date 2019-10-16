    public void testCancelCancelableDialog() {
        DialogInterface.OnCancelListener mockCancelListener =
                mock(DialogInterface.OnCancelListener.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivityTestRule.getActivity())
                .setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_content)
                .setCancelable(true)
                .setOnCancelListener(mockCancelListener);
        wireBuilder(builder);

        onView(withId(R.id.test_button)).perform(click());

        // Emulate a tap on the device BACK button
        Espresso.pressBack();

        // Since our dialog is cancelable, check that the cancel listener has been invoked
        verify(mockCancelListener, times(1)).onCancel(mAlertDialog);
    }
