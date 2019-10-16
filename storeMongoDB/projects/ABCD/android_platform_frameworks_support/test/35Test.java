    public void testCustomView() {
        final Context context = mActivityTestRule.getActivity();
        final LayoutInflater inflater = LayoutInflater.from(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_content)
                .setView(inflater.inflate(R.layout.alert_dialog_custom_view, null, false));
        wireBuilder(builder);

        onView(withId(R.id.test_button)).perform(click());

        verifyCustomView();
    }
