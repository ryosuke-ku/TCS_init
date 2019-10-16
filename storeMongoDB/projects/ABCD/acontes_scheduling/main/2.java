    public static boolean checkOSArch(String osArch) {
        if (osArch == null) {
            System.err.println("Given OS architecture was NULL");
            return false;
        }

        String localOSArch = System.getProperty("os.arch");
        if (localOSArch.toUpperCase().contains(osArch.toUpperCase())) {
            return true;
        }
        return false;
    }
