    public void setPathType(String pathType)
    {
        if (pathType == null)
        {
            String msg = Logging.getMessage("nullValue.PathTypeIsNull");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        this.setPathType(pathType.equals(AVKey.GREAT_CIRCLE) ? GREAT_CIRCLE
            : pathType.equals(AVKey.RHUMB_LINE) ? RHUMB_LINE : LINEAR);
    }
