    private static void assertEquals(Polyline expected, Polyline actual)
    {
        assertNotNull("Expected is null", expected);
        assertNotNull("Actual is null", actual);

        assertEquals("color", expected.getColor(), actual.getColor());
        assertEquals("highlightColor", expected.getColor(), actual.getColor());
        assertEquals("antiAliasHint", expected.getAntiAliasHint(), actual.getAntiAliasHint());
        assertEquals("filled", expected.isFilled(), actual.isFilled());
        assertEquals("closed", expected.isClosed(), actual.isClosed());
        assertEquals("highlighted", expected.isFilled(), actual.isFilled());
        assertEquals("pathType", expected.getPathType(), actual.getPathType());
        assertEquals("followTerrain", expected.isFollowTerrain(), actual.isFollowTerrain());
        assertEquals("offset", expected.getOffset(), actual.getOffset());
        assertEquals("terrainConformance", expected.getTerrainConformance(), actual.getTerrainConformance());
        assertEquals("lineWidth", expected.getLineWidth(), actual.getLineWidth());
        assertEquals("stipplePattern", expected.getStipplePattern(), actual.getStipplePattern());
        assertEquals("stippleFactor", expected.getStippleFactor(), actual.getStippleFactor());
        assertEquals("numSubsegments", expected.getNumSubsegments(), actual.getNumSubsegments());
        // Position does not override equals(), so we must compare the contents of "positions" ourselves.
        Iterator<Position> expectedPositions = expected.getPositions().iterator();
        Iterator<Position> actualPositions = actual.getPositions().iterator();
        while (expectedPositions.hasNext() && actualPositions.hasNext())
        {
            Position expectedPos = expectedPositions.next(), actualPos = actualPositions.next();
            assertEquals("positions.i.latitude", expectedPos.getLatitude(), actualPos.getLatitude());
            assertEquals("positions.i.longitude", expectedPos.getLongitude(), actualPos.getLongitude());
            assertEquals("positions.i.elevation", expectedPos.getElevation(), actualPos.getElevation());
        }
        // If either iterator has more elements, then their lengths are different.
        assertFalse("positions.length", expectedPositions.hasNext() || actualPositions.hasNext());
    }
