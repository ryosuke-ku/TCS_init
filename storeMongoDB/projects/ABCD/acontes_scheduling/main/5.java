    public static boolean checkFreeMemory(long requiredMemory) {
        if (Runtime.getRuntime().freeMemory() >= requiredMemory) {
            return true;
        }
        return false;
    }
