    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
            fileModified = true;
        }
        this.previousLastModified = currentLastModified;
        return fileModified;
    }
