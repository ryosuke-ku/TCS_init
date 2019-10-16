    public static boolean checkOSVersion(String osVersion) {
        if (osVersion == null) {
            System.err.println("Given OS version was NULL");
            return false;
        }

        String localOSVersion = System.getProperty("os.version");
        if (localOSVersion.contains(osVersion)) {
            return true;
        }
        return false;
    }
