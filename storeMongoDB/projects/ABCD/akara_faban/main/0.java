    public static TagEngine getInstance()
            throws IOException, ClassNotFoundException {
        // There should not be concurrency on first access at all.
        // We know, double-checks are not absolutely safe, but are by far safe
        // enough for the task at hand. We also do not want to pay the
        // synchronize overhead for every getInstance call.
        if (instance == null) {
            synchronized (TagEngine.class) {
                if (instance == null)
                    instance = createInstance();
            }
        }            
        return instance;
    }
     }
    }
