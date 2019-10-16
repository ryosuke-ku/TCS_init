    public void testSingleChoiceItemsFromRuntimeArray() {
        final String[] content = new String[] { "Alice", "Bob", "Charlie", "Delta" };
        final DialogInterface.OnClickListener mockClickListener =
                mock(DialogInterface.OnClickListener.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivityTestRule.getActivity())
                .setTitle(R.string.alert_dialog_title)
                .setSingleChoiceItems(content, 2, mockClickListener);
        wireBuilder(builder);

        verifySingleChoiceItemsContent(content, 2, mockClickListener);
    }
