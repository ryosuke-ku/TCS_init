    public void testAttachImage() throws InterruptedException {
        final String method = "testAttachImage";
        MyLog.v(this, method + " started");

        View editorView = getActivity().findViewById(R.id.message_editor);
        ActivityTestHelper<TimelineActivity> helper = new ActivityTestHelper<>(this, getActivity());
        helper.clickMenuItem(method + " clicker createMessageButton", R.id.createMessageButton);
        ActivityTestHelper.waitViewVisible(method + "; Editor appeared", editorView);
        assertTextCleared();

        String body = "Message with attachment " + TestSuite.TESTRUN_UID;
        EditText editText = (EditText) editorView.findViewById(R.id.messageBodyEditText);
        editText.requestFocus();
        TestSuite.waitForIdleSync(this);
        getInstrumentation().sendStringSync(body);
        TestSuite.waitForIdleSync(this);

        getActivity().selectorActivityMock = helper;
        helper.clickMenuItem(method + " clicker attach_menu_id", R.id.attach_menu_id);
        assertNotNull(helper.waitForSelectorStart(method, ActivityRequestCode.ATTACH.id));
        getActivity().selectorActivityMock = null;

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HelpActivity.class.getName(), null, false);

        Intent intent = new Intent(getActivity(), HelpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().getApplicationContext().startActivity(intent);

        Activity selectorActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 15000);
        assertTrue(selectorActivity != null);
        ActivityTestHelper.waitViewInvisible(method, editorView);
        DbUtils.waitMs(method, 4000);
        selectorActivity.finish();

        MyLog.i(method, "Callback from a selector");
        Intent data = new Intent();
        data.setData(TestSuite.LOCAL_IMAGE_TEST_URI2);
        getActivity().onActivityResult(ActivityRequestCode.ATTACH.id, Activity.RESULT_OK, data);

        final MessageEditor editor = getActivity().getMessageEditor();
        for (int attempt=0; attempt < 4; attempt++) {
            ActivityTestHelper.waitViewVisible(method, editorView);
            // Due to a race the editor may open before this change first.
            if (TestSuite.LOCAL_IMAGE_TEST_URI2.equals(editor.getData().getMediaUri())) {
                break;
            }
            if (DbUtils.waitMs(method, 2000)) {
                break;
            }
        }
        assertEquals("Image attached", TestSuite.LOCAL_IMAGE_TEST_URI2, editor.getData().getMediaUri());
        assertEquals("Text is the same", body, editText.getText().toString().trim());

        MyLog.v(this, method + " ended");
    }
