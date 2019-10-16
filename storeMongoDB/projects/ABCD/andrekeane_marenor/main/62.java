    public void restoreState(String stateInXml)
    {
        if (stateInXml == null)
        {
            String message = Logging.getMessage("nullValue.StringIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        RestorableSupport restorableSupport;
        try
        {
            restorableSupport = RestorableSupport.parse(stateInXml);
        }
        catch (Exception e)
        {
            // Parsing the document specified by stateInXml failed.
            String message = Logging.getMessage("generic.ExceptionAttemptingToParseStateXml", stateInXml);
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message, e);
        }

        // Restore application set attributes from under the document root.
        restoreAttributes(restorableSupport, null, this);

        // Restore application set default attributes from under the "defaultAttributes" state element.
        RestorableSupport.StateObject defaultAttributesStateObj =
            restorableSupport.getStateObject("defaultAttributes");
        if (defaultAttributesStateObj != null)
        {
            AnnotationAttributes newDefaultAttributes = this.defaultAttributes;
            // We do not want to write to the static member "defaults". So if this AnnotationAttributes' does not
            // have it's own defaultAttributes instance, we create one for it
            if (newDefaultAttributes == AnnotationAttributes.defaults)
                newDefaultAttributes = new AnnotationAttributes();
            restoreAttributes(restorableSupport, defaultAttributesStateObj, newDefaultAttributes);
            setDefaults(newDefaultAttributes);
        }
    }
