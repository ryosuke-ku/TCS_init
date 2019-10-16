    public void testCustomAdapter() {
        final Context context = mActivityTestRule.getActivity();
        final String[] content = context.getResources().getStringArray(R.array.alert_dialog_items);
        final DialogInterface.OnClickListener mockClickListener =
                mock(DialogInterface.OnClickListener.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivityTestRule.getActivity())
                .setTitle(R.string.alert_dialog_title)
                .setAdapter(
                        new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, content),
                        mockClickListener);
        wireBuilder(builder);

        verifySimpleItemsContent(content, mockClickListener);
    }
