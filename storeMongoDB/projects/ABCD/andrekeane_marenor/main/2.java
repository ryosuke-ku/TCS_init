    public void restoreState(RestorableSupport restorableSupport, RestorableSupport.StateObject context)
    {
        if (restorableSupport == null)
        {
            String message = Logging.getMessage("nullValue.RestorableSupportIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        RestorableSupport.StateObject so = restorableSupport.getStateObject(context, "width");
        if (so != null)
        {
            String mode = restorableSupport.getStateValueAsString(so, "mode");
            mode = convertLegacyModeString(mode);

            Double param = restorableSupport.getStateValueAsDouble(so, "param");
            String units = restorableSupport.getStateValueAsString(so, "units");

            // Restore the width only when the mode and param are specified. null is an acceptable value for units.
            if (mode != null && param != null)
                this.setWidth(mode, param, units);
        }

        so = restorableSupport.getStateObject(context, "height");
        if (so != null)
        {
            String mode = restorableSupport.getStateValueAsString(so, "mode");
            mode = convertLegacyModeString(mode);

            Double param = restorableSupport.getStateValueAsDouble(so, "param");
            String units = restorableSupport.getStateValueAsString(so, "units");

            // Restore the height only when the mode and param are specified. null is an acceptable value for units.
            if (mode != null && param != null)
                this.setHeight(mode, param, units);
        }
    }
