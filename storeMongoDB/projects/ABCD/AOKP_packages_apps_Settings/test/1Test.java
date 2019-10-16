    public void testGetFormattedKernelVersion() throws Exception {
        if ("Unavailable".equals(DeviceInfoSettings.getFormattedKernelVersion())) {
            fail("formatKernelVersion can't cope with this device's /proc/version");
        }
    }
