    public void getRestorableState(RestorableSupport restorableSupport, RestorableSupport.StateObject context)
    {
        if (restorableSupport == null)
        {
            String message = Logging.getMessage("nullValue.RestorableSupportIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        RestorableSupport.StateObject so = restorableSupport.addStateObject(context, "width");
        if (so != null)
        {
            restorableSupport.addStateValueAsString(so, "mode", this.getWidthMode());
            restorableSupport.addStateValueAsDouble(so, "param", this.getWidth());

            if (this.getWidthUnits() != null)
                restorableSupport.addStateValueAsString(so, "units", this.getWidthUnits());
        }

        so = restorableSupport.addStateObject(context, "height");
        if (so != null)
        {
            restorableSupport.addStateValueAsString(so, "mode", this.getHeightMode());
            restorableSupport.addStateValueAsDouble(so, "param", this.getHeight());

            if (this.getHeightUnits() != null)
                restorableSupport.addStateValueAsString(so, "units", this.getHeightUnits());
        }
    }
