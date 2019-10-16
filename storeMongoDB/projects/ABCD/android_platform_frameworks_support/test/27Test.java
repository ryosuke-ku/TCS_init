    private void verifyDialogButtons(@StringRes int positiveButtonTextResId,
            @StringRes int negativeButtonTextResId,
            @StringRes int neutralButtonTextResId, int whichButtonToClick) {
        Context context = mActivityTestRule.getActivity();
        String positiveButtonText = null;
        String negativeButtonText = null;
        String neutralButtonText = null;

        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.alert_dialog_title);
        DialogInterface.OnClickListener mockClickListener =
                mock(DialogInterface.OnClickListener.class);
        // Configure buttons with non-zero text resource IDs
        if (positiveButtonTextResId != 0) {
            positiveButtonText = context.getString(positiveButtonTextResId);
            builder.setPositiveButton(positiveButtonTextResId, mockClickListener);
        }
        if (negativeButtonTextResId != 0) {
            negativeButtonText = context.getString(negativeButtonTextResId);
            builder.setNegativeButton(negativeButtonTextResId, mockClickListener);
        }
        if (neutralButtonTextResId != 0) {
            neutralButtonText = context.getString(neutralButtonTextResId);
            builder.setNeutralButton(neutralButtonTextResId, mockClickListener);
        }
        // Set a dismiss listener to verify that the dialog is dismissed on clicking any button
        DialogInterface.OnDismissListener mockDismissListener =
                mock(DialogInterface.OnDismissListener.class);
        builder.setOnDismissListener(mockDismissListener);

        // Wire the builder to the button click and click that button to show the dialog
        wireBuilder(builder);
        onView(withId(R.id.test_button)).perform(click());

        // Check that the dialog is showing the configured buttons
        verifyButtonContent(positiveButtonText, negativeButtonText, neutralButtonText);

        // Click the specified button and verify the post-click state
        String textOfButtonToClick = null;
        switch (whichButtonToClick) {
            case DialogInterface.BUTTON_POSITIVE:
                textOfButtonToClick = positiveButtonText;
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                textOfButtonToClick = negativeButtonText;
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                textOfButtonToClick = neutralButtonText;
                break;
        }
        onView(withText(textOfButtonToClick)).inRoot(isDialog()).perform(click());
        verifyPostButtonClickState(whichButtonToClick, mockClickListener, mockDismissListener);
    }
