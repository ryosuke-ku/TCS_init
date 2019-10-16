    public Object load(ClassLoader cl, Bus b) {
        if (obj != null) {
            return obj;
        }
        Class<?> cls = getClassObject(cl);
        try {
            if (notFound) {
                return null;
            }
            try {
                //if there is a Bus constructor, use it.
                if (b != null && args == null) {
                    Constructor<?> con = cls.getConstructor(Bus.class);
                    obj = con.newInstance(b);
                    return obj;
                } else if (b != null && args != null) {
                    Constructor<?> con;
                    boolean noBus = false;
                    try {
                        con = cls.getConstructor(Bus.class, Object[].class);
                    } catch (Exception ex) {
                        con = cls.getConstructor(Object[].class);
                        noBus = true;
                    }
                    if (noBus) {
                        obj = con.newInstance(args);
                    } else {
                        obj = con.newInstance(b, args);
                    }
                    return obj;                    
                } else if (args != null) {
                    Constructor<?> con = cls.getConstructor(Object[].class);
                    obj = con.newInstance(args);
                    return obj;                    
                }
            } catch (InvocationTargetException ex) {
                throw new ExtensionException(new Message("PROBLEM_CREATING_EXTENSION_CLASS", LOG, cls.getName()), 
                                             ex.getCause());
            } catch (InstantiationException ex) {
                throw new ExtensionException(new Message("PROBLEM_CREATING_EXTENSION_CLASS", LOG, cls.getName()), ex);
            } catch (SecurityException ex) {
                throw new ExtensionException(new Message("PROBLEM_CREATING_EXTENSION_CLASS", LOG, cls.getName()), ex);
            } catch (NoSuchMethodException e) {
                //ignore
            }
            obj = cls.getConstructor().newInstance();
        } catch (ExtensionException ex) {
            notFound = true;
            if (!optional) {
                throw ex;
            } else {
                LOG.log(Level.FINE, "Could not load optional extension " + getName(), (Throwable)ex);
            }
        } catch (InvocationTargetException ex) {
            notFound = true;
            if (!optional) {
                throw new ExtensionException(new Message("PROBLEM_CREATING_EXTENSION_CLASS", LOG, cls.getName()), 
                                             ex.getCause());
            } else {
                LOG.log(Level.FINE, "Could not load optional extension " + getName(), (Throwable)ex);
            }
        } catch (NoSuchMethodException ex) {
            notFound = true;
            List<Object> a = new ArrayList<Object>();
            if (b != null) {
                a.add(b);
            }
            if (args != null) {
                a.add(args);
            }
            if (!optional) {
                throw new ExtensionException(new Message("PROBLEM_FINDING_CONSTRUCTOR", LOG,
                                                         cls.getName(), a), ex);
            } else {
                LOG.log(Level.FINE, "Could not load optional extension " + getName(), (Throwable)ex);
            }
        } catch (Throwable e) {
            notFound = true;
            if (!optional) {
                throw new ExtensionException(new Message("PROBLEM_CREATING_EXTENSION_CLASS", LOG, cls.getName()), e);
            } else {
                LOG.log(Level.FINE, "Could not load optional extension " + getName(), (Throwable)e);
            }
        }
        return obj;
    }
