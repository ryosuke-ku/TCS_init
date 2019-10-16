    public void setBacklightBrightness(int brightness)
    {
        try {
            mService.setBacklightBrightness(brightness);
        } catch (RemoteException e) {
        }
    }
