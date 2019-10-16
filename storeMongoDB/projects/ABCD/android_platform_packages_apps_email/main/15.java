    protected static Account fromJson(final JSONObject json) {
        try {
            final Account a = new Account();
            a.mDisplayName = json.optString(AccountColumns.DISPLAY_NAME);
            a.mEmailAddress = json.getString(AccountColumns.EMAIL_ADDRESS);
            // SYNC_KEY is not stored
            a.mSyncLookback = json.getInt(AccountColumns.SYNC_LOOKBACK);
            a.mSyncInterval = json.getInt(AccountColumns.SYNC_INTERVAL);
            final JSONObject recvJson = json.getJSONObject(JSON_TAG_HOST_AUTH_RECV);
            a.mHostAuthRecv = HostAuth.fromJson(recvJson);
            final JSONObject sendJson = json.optJSONObject(JSON_TAG_HOST_AUTH_SEND);
            if (sendJson != null) {
                a.mHostAuthSend = HostAuth.fromJson(sendJson);
            }
            a.mFlags = json.getInt(AccountColumns.FLAGS);
            a.mSenderName = json.optString(AccountColumns.SENDER_NAME);
            a.mProtocolVersion = json.optString(AccountColumns.PROTOCOL_VERSION);
            // SECURITY_SYNC_KEY is not stored
            a.mSignature = json.optString(AccountColumns.SIGNATURE);
            // POLICY_KEY is not stored
            a.mPingDuration = json.optInt(AccountColumns.PING_DURATION, 0);
            return a;
        } catch (final JSONException e) {
            LogUtils.d(LogUtils.TAG, e, "Exception while deserializing Account");
        }
        return null;
    }
