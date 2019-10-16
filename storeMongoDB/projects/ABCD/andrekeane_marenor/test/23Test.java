    private static void assignExampleValues(Polyline polyline)
    {
        polyline.setColor(Color.MAGENTA);
        polyline.setHighlightColor(Color.CYAN);
        polyline.setAntiAliasHint(Polyline.ANTIALIAS_NICEST);
        polyline.setFilled(true);
        polyline.setClosed(true);
        polyline.setHighlighted(true);
        polyline.setPathType(Polyline.GREAT_CIRCLE);
        polyline.setFollowTerrain(true);
        polyline.setOffset(10.5);
        polyline.setTerrainConformance(0.5);
        polyline.setLineWidth(3.5);
        polyline.setStipplePattern((short) 0xfc0c);
        polyline.setStippleFactor(128);
        polyline.setNumSubsegments(100);
        polyline.setPositions(Arrays.asList(
            Position.fromDegrees(2, 3, 0.5),
            Position.fromDegrees(4, 9, 111.5),
            Position.fromDegrees(8, 27, 222.5)));
    }
