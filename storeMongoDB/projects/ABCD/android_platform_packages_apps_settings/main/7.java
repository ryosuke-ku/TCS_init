    public boolean launchConfirmationActivityWithExternalAndChallenge(int request,
            @Nullable CharSequence title, @Nullable CharSequence header,
            @Nullable CharSequence description, boolean external, long challenge, int userId) {
        return launchConfirmationActivity(request, title, header, description, false,
                external, true, challenge, Utils.enforceSameOwner(mActivity, userId));
    }
