    public void testButtonVisibility() {
        final String positiveButtonText = "Positive button";
        final String negativeButtonText = "Negative button";
        final String neutralButtonText = "Neutral button";
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivityTestRule.getActivity())
                .setTitle(R.string.alert_dialog_title)
                .setPositiveButton(positiveButtonText, null)
                .setNegativeButton(negativeButtonText, null)
                .setNeutralButton(neutralButtonText, null);
        wireBuilder(builder);

        onView(withId(R.id.test_button)).perform(click());

        // Positive button should be fully displayed with no text eliding
        onView(withText(positiveButtonText)).inRoot(isDialog()).check(
                matches(isCompletelyDisplayed()));
        onView(withText(positiveButtonText)).inRoot(isDialog()).check(
                matches(not(hasEllipsizedText())));

        // Negative button should be fully displayed with no text eliding
        onView(withText(negativeButtonText)).inRoot(isDialog()).check(
                matches(isCompletelyDisplayed()));
        onView(withText(negativeButtonText)).inRoot(isDialog()).check(
                matches(not(hasEllipsizedText())));

        // Neutral button should be fully displayed with no text eliding
        onView(withText(neutralButtonText)).inRoot(isDialog()).check(
                matches(isCompletelyDisplayed()));
        onView(withText(neutralButtonText)).inRoot(isDialog()).check(
                matches(not(hasEllipsizedText())));
    }
