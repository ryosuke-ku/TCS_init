    public String getRestorableState()
    {
        RestorableSupport restorableSupport = RestorableSupport.newRestorableSupport();
        // Creating a new RestorableSupport failed. RestorableSupport logged the problem, so just return null.
        if (restorableSupport == null)
            return null;

        // Save application set attributes to the document root.
        saveAttributes(this, restorableSupport, null);

        // We only save this AnnotationAttributes' defaultAttributes when the application has set them to
        // something other than the static member "defaults".
        if (this.defaultAttributes != AnnotationAttributes.defaults)
        {
            RestorableSupport.StateObject defaultAttributesStateObj =
                restorableSupport.addStateObject("defaultAttributes");
            saveAttributes(this.defaultAttributes, restorableSupport, defaultAttributesStateObj);
        }

        return restorableSupport.getStateAsXml();
    }
