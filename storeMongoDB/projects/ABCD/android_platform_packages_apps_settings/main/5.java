    private boolean launchConfirmationActivity(int request, CharSequence title, CharSequence header,
            CharSequence message, Class<?> activityClass, boolean returnCredentials,
            boolean external, boolean hasChallenge, long challenge,
            int userId) {
        final Intent intent = new Intent();
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.TITLE_TEXT, title);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.HEADER_TEXT, header);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.DETAILS_TEXT, message);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.ALLOW_FP_AUTHENTICATION, external);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.DARK_THEME, external);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.SHOW_CANCEL_BUTTON, external);
        intent.putExtra(ConfirmDeviceCredentialBaseFragment.SHOW_WHEN_LOCKED, external);
        intent.putExtra(ChooseLockSettingsHelper.EXTRA_KEY_RETURN_CREDENTIALS, returnCredentials);
        intent.putExtra(ChooseLockSettingsHelper.EXTRA_KEY_HAS_CHALLENGE, hasChallenge);
        intent.putExtra(ChooseLockSettingsHelper.EXTRA_KEY_CHALLENGE, challenge);
        // we should never have a drawer when confirming device credentials.
        intent.putExtra(SettingsActivity.EXTRA_HIDE_DRAWER, true);
        intent.putExtra(Intent.EXTRA_USER_ID, userId);
        intent.setClassName(ConfirmDeviceCredentialBaseFragment.PACKAGE, activityClass.getName());
        if (external) {
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            if (mFragment != null) {
                copyOptionalExtras(mFragment.getActivity().getIntent(), intent);
                mFragment.startActivity(intent);
            } else {
                copyOptionalExtras(mActivity.getIntent(), intent);
                mActivity.startActivity(intent);
            }
        } else {
            if (mFragment != null) {
                mFragment.startActivityForResult(intent, request);
            } else {
                mActivity.startActivityForResult(intent, request);
            }
        }
        return true;
    }
