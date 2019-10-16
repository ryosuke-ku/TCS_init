    public String getRestorableState()
    {
        RestorableSupport rs = RestorableSupport.newRestorableSupport();
        // Creating a new RestorableSupport failed. RestorableSupport logged the problem, so just return null.
        if (rs == null)
            return null;

        if (this.color != null)
        {
            String encodedColor = RestorableSupport.encodeColor(this.color);
            if (encodedColor != null)
                rs.addStateValueAsString("color", encodedColor);
        }

        if (this.highlightColor != null)
        {
            String encodedColor = RestorableSupport.encodeColor(this.highlightColor);
            if (encodedColor != null)
                rs.addStateValueAsString("highlightColor", encodedColor);
        }

        if (this.positions != null)
        {
            // Create the base "positions" state object.
            RestorableSupport.StateObject positionsStateObj = rs.addStateObject("positions");
            if (positionsStateObj != null)
            {
                for (Position p : this.positions)
                {
                    // Save each position only if all parts (latitude, longitude, and elevation) can be
                    // saved. We will not save a partial iconPosition (for example, just the elevation).
                    if (p != null && p.getLatitude() != null && p.getLongitude() != null)
                    {
                        // Create a nested "position" element underneath the base "positions".
                        RestorableSupport.StateObject pStateObj =
                            rs.addStateObject(positionsStateObj, "position");
                        if (pStateObj != null)
                        {
                            rs.addStateValueAsDouble(pStateObj, "latitudeDegrees",
                                p.getLatitude().degrees);
                            rs.addStateValueAsDouble(pStateObj, "longitudeDegrees",
                                p.getLongitude().degrees);
                            rs.addStateValueAsDouble(pStateObj, "elevation",
                                p.getElevation());
                        }
                    }
                }
            }
        }

        rs.addStateValueAsInteger("antiAliasHint", this.antiAliasHint);
        rs.addStateValueAsBoolean("filled", this.filled);
        rs.addStateValueAsBoolean("closed", this.closed);
        rs.addStateValueAsBoolean("highlighted", this.highlighted);
        rs.addStateValueAsInteger("pathType", this.pathType);
        rs.addStateValueAsBoolean("followTerrain", this.followTerrain);
        rs.addStateValueAsDouble("offset", this.offset);
        rs.addStateValueAsDouble("terrainConformance", this.terrainConformance);
        rs.addStateValueAsDouble("lineWidth", this.lineWidth);
        rs.addStateValueAsInteger("stipplePattern", this.stipplePattern);
        rs.addStateValueAsInteger("stippleFactor", this.stippleFactor);
        rs.addStateValueAsInteger("numSubsegments", this.numSubsegments);

        RestorableSupport.StateObject so = rs.addStateObject(null, "avlist");
        for (Map.Entry<String, Object> avp : this.getEntries())
        {
            this.getRestorableStateForAVPair(avp.getKey(), avp.getValue() != null ? avp.getValue() : "", rs, so);
        }

        return rs.getStateAsXml();
    }
