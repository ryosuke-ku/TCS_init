    public static boolean checkExec(String fileName) {
        String path = System.getenv("PATH");
        String[] tokens = path.split(File.pathSeparator);
        for (String folder : tokens) {
            // Browse each folder
            File directory = new File(folder);

            if (!directory.exists()) {
                System.err.println(folder + " doesn't exist");
            } else if (!directory.isDirectory()) {
                System.err.println(folder + "' is not a directory");
            } else {
                File[] subfiles = directory.listFiles();
                for (int i = 0; i < subfiles.length; i++) {
                    // check if it matches
                    if (subfiles[i].getName().equals(fileName)) {
                        return true;
                    }
                }
            }
        }
        System.err.println(fileName + " is not in PATH environment variable.");
        return false;
    }
