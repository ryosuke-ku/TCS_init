    public void testlaunchConfirmationActivityWithExternalAndChallenge() {

        final int userId = UserHandle.myUserId();
        final int request = 100;
        final long challenge = 10000L;
        {
            // Test external == true
            final boolean external = true;

            final Activity mockActivity = getMockActivity();
            ChooseLockSettingsHelper helper = getChooseLockSettingsHelper(mockActivity);
            helper.launchConfirmationActivityWithExternalAndChallenge(
                    request, // request
                    "title",
                    "header",
                    "description",
                    external,
                    challenge,
                    userId
            );
            final ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
            verify(mockActivity, times(1)).startActivity(intentCaptor.capture());
            Intent capturedIntent = getResultIntent(intentCaptor);

            assertEquals(new ComponentName("com.android.settings",
                            ConfirmLockPattern.InternalActivity.class.getName()),
                    capturedIntent.getComponent());
            assertFalse(capturedIntent.getBooleanExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_RETURN_CREDENTIALS, false));
            assertTrue(capturedIntent.getBooleanExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_HAS_CHALLENGE, false));
            assertEquals(challenge, capturedIntent.getLongExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_CHALLENGE, 0L));
            assertEquals(external,
                    (capturedIntent.getFlags() & Intent.FLAG_ACTIVITY_FORWARD_RESULT) != 0);
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.ALLOW_FP_AUTHENTICATION, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.DARK_THEME, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.SHOW_CANCEL_BUTTON, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.SHOW_WHEN_LOCKED, false));
        }

        {
            // Test external == false
            final boolean external = false;

            final Activity mockActivity = getMockActivity();
            ChooseLockSettingsHelper helper = getChooseLockSettingsHelper(mockActivity);
            helper.launchConfirmationActivityWithExternalAndChallenge(
                    request, // request
                    "title",
                    "header",
                    "description",
                    external,
                    challenge,
                    userId
            );
            final ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
            verify(mockActivity, times(1)).startActivityForResult(intentCaptor.capture(),
                    eq(request));
            Intent capturedIntent = getResultIntent(intentCaptor);


            assertEquals(new ComponentName("com.android.settings",
                            ConfirmLockPattern.InternalActivity.class.getName()),
                    capturedIntent.getComponent());
            assertFalse(capturedIntent.getBooleanExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_RETURN_CREDENTIALS, false));
            assertTrue(capturedIntent.getBooleanExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_HAS_CHALLENGE, false));
            assertEquals(challenge, capturedIntent.getLongExtra(
                    ChooseLockSettingsHelper.EXTRA_KEY_CHALLENGE, 0L));
            assertEquals(external,
                    (capturedIntent.getFlags() & Intent.FLAG_ACTIVITY_FORWARD_RESULT) != 0);
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.ALLOW_FP_AUTHENTICATION, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.DARK_THEME, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.SHOW_CANCEL_BUTTON, false));
            assertEquals(external, capturedIntent.getBooleanExtra(
                    ConfirmDeviceCredentialBaseFragment.SHOW_WHEN_LOCKED, false));
        }
    }
