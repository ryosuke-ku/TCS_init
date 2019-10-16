    static public String getDeviceId(Context context) throws IOException {
        if (sDeviceId == null) {
            sDeviceId = new AccountServiceProxy(context).getDeviceId();
            alwaysLog("Received deviceId from Email app: " + sDeviceId);
        }
        return sDeviceId;
    }
