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

        String colorState = restorableSupport.getStateValueAsString("color");
        if (colorState != null)
        {
            Color color = RestorableSupport.decodeColor(colorState);
            if (color != null)
                setColor(color);
        }

        colorState = restorableSupport.getStateValueAsString("highlightColor");
        if (colorState != null)
        {
            Color color = RestorableSupport.decodeColor(colorState);
            if (color != null)
                setHighlightColor(color);
        }

        // Get the base "positions" state object.
        RestorableSupport.StateObject positionsStateObj = restorableSupport.getStateObject("positions");
        if (positionsStateObj != null)
        {
            ArrayList<Position> newPositions = new ArrayList<Position>();
            // Get the nested "position" states beneath the base "positions".
            RestorableSupport.StateObject[] positionStateArray =
                restorableSupport.getAllStateObjects(positionsStateObj, "position");
            if (positionStateArray != null && positionStateArray.length != 0)
            {
                for (RestorableSupport.StateObject pStateObj : positionStateArray)
                {
                    if (pStateObj != null)
                    {
                        // Restore each position only if all parts are available.
                        // We will not restore a partial position (for example, just the elevation).
                        Double latitudeState = restorableSupport.getStateValueAsDouble(pStateObj, "latitudeDegrees");
                        Double longitudeState = restorableSupport.getStateValueAsDouble(pStateObj, "longitudeDegrees");
                        Double elevationState = restorableSupport.getStateValueAsDouble(pStateObj, "elevation");
                        if (latitudeState != null && longitudeState != null && elevationState != null)
                            newPositions.add(Position.fromDegrees(latitudeState, longitudeState, elevationState));
                    }
                }
            }

            // Even if there are no actual positions specified, we set positions as an empty list.
            // An empty set of positions is still a valid state.
            setPositions(newPositions);
        }

        Integer antiAliasHintState = restorableSupport.getStateValueAsInteger("antiAliasHint");
        if (antiAliasHintState != null)
            setAntiAliasHint(antiAliasHintState);

        Boolean isFilledState = restorableSupport.getStateValueAsBoolean("filled");
        if (isFilledState != null)
            setFilled(isFilledState);

        Boolean isClosedState = restorableSupport.getStateValueAsBoolean("closed");
        if (isClosedState != null)
            setClosed(isClosedState);

        Boolean isHighlightedState = restorableSupport.getStateValueAsBoolean("highlighted");
        if (isHighlightedState != null)
            setHighlighted(isHighlightedState);

        Integer pathTypeState = restorableSupport.getStateValueAsInteger("pathType");
        if (pathTypeState != null)
            setPathType(pathTypeState);

        Boolean isFollowTerrainState = restorableSupport.getStateValueAsBoolean("followTerrain");
        if (isFollowTerrainState != null)
            setFollowTerrain(isFollowTerrainState);

        Double offsetState = restorableSupport.getStateValueAsDouble("offset");
        if (offsetState != null)
            setOffset(offsetState);

        Double terrainConformanceState = restorableSupport.getStateValueAsDouble("terrainConformance");
        if (terrainConformanceState != null)
            setTerrainConformance(terrainConformanceState);

        Double lineWidthState = restorableSupport.getStateValueAsDouble("lineWidth");
        if (lineWidthState != null)
            setLineWidth(lineWidthState);

        Integer stipplePatternState = restorableSupport.getStateValueAsInteger("stipplePattern");
        if (stipplePatternState != null)
            setStipplePattern(stipplePatternState.shortValue());

        Integer stippleFactorState = restorableSupport.getStateValueAsInteger("stippleFactor");
        if (stippleFactorState != null)
            setStippleFactor(stippleFactorState);

        Integer numSubsegmentsState = restorableSupport.getStateValueAsInteger("numSubsegments");
        if (numSubsegmentsState != null)
            setNumSubsegments(numSubsegmentsState);

        RestorableSupport.StateObject so = restorableSupport.getStateObject(null, "avlist");
        if (so != null)
        {
            RestorableSupport.StateObject[] avpairs = restorableSupport.getAllStateObjects(so, "");
            if (avpairs != null)
            {
                for (RestorableSupport.StateObject avp : avpairs)
                {
                    if (avp != null)
                        this.setValue(avp.getName(), avp.getValue());
                }
            }
        }
    }
