    protected JSONObject toJson() {
        try {
            final JSONObject json = new JSONObject();
            json.putOpt(AccountColumns.DISPLAY_NAME, mDisplayName);
            json.put(AccountColumns.EMAIL_ADDRESS, mEmailAddress);
            json.put(AccountColumns.SYNC_LOOKBACK, mSyncLookback);
            json.put(AccountColumns.SYNC_INTERVAL, mSyncInterval);
            final JSONObject recvJson = mHostAuthRecv.toJson();
            json.put(JSON_TAG_HOST_AUTH_RECV, recvJson);
            if (mHostAuthSend != null) {
                final JSONObject sendJson = mHostAuthSend.toJson();
                json.put(JSON_TAG_HOST_AUTH_SEND, sendJson);
            }
            json.put(AccountColumns.FLAGS, mFlags);
            json.putOpt(AccountColumns.SENDER_NAME, mSenderName);
            json.putOpt(AccountColumns.PROTOCOL_VERSION, mProtocolVersion);
            json.putOpt(AccountColumns.SIGNATURE, mSignature);
            json.put(AccountColumns.PING_DURATION, mPingDuration);
            return json;
        } catch (final JSONException e) {
            LogUtils.d(LogUtils.TAG, e, "Exception while serializing Account");
        }
        return null;
    }
