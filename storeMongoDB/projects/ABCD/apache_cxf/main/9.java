    public Class<?> loadInterface(ClassLoader cl) {
        if (intf != null || notFound) {
            return intf;
        }
        synchronized (this) {
            if (intf == null) {
                intf = tryClass(interfaceName, cl);
            }
        }
        return intf;
    }
