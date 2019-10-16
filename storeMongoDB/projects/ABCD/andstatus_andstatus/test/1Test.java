    private void openEditor() throws InterruptedException {
        final String method = "openEditor";
        MenuItem createMessageButton = getActivity().getOptionsMenu().findItem(R.id.createMessageButton);
        assertTrue(createMessageButton != null);
        View editorView = getActivity().findViewById(R.id.message_editor);
        assertTrue(editorView != null);
        if (editorView.getVisibility() != android.view.View.VISIBLE) {
            assertTrue("Blog button is visible", createMessageButton.isVisible());
            ActivityTestHelper<TimelineActivity> helper = new ActivityTestHelper<>(this, getActivity());
            helper.clickMenuItem(method + " opening editor", R.id.createMessageButton);
        }
        assertEquals("Editor appeared", android.view.View.VISIBLE, editorView.getVisibility());
    }
