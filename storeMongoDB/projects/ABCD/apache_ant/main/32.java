            Object create(final Project project, final Object parent, final Object ignore)
                    throws InvocationTargetException, IllegalAccessException {
                if (!getMethod().getName().endsWith("Configured")) {
                    getMethod().invoke(parent, new Object[] {realObject});
                }
                return nestedObject;
            }
