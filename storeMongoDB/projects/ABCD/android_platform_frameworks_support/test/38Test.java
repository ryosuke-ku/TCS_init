    public void testCustomTitlePostCreation() {
        final Context context = mActivityTestRule.getActivity();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_content);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog = builder.create();

                // Configure custom title
                mAlertDialog.setCustomTitle(inflater.inflate(
                        R.layout.alert_dialog_custom_title, null, false));

                mAlertDialog.show();
            }
        });

        // Click the button to create the dialog, configure custom title and show the dialog
        onView(withId(R.id.test_button)).perform(click());

        verifyCustomTitle();
    }
