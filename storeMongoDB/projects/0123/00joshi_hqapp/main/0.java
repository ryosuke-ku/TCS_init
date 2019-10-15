    public static boolean addDefaultExclude(final String s) {
        synchronized (defaultExcludes) {
            return defaultExcludes.add(s);
        }
    }
e cause = error.getCause();
            if (cause == null) {
                break;
            }
            String msg1 = error.toString();
            String msg2 = cause.toString();
            if (msg1.endsWith(msg2)) {
                m.append(msg1, 0, msg1.length() - msg2.length());
                error = cause;
            } else {
                break;
            }
        }
        if (verbose || !(error instanceof BuildException)) {
            m.append(StringUtils.getStackTrace(error));
        } else {
            m.append(String.format("%s%n", error));
        }
    }
