    public void testSaveAndRestoreOnNewObject_Polyline()
    {
        Polyline polyline = new Polyline();
        assignExampleValues(polyline);

        String stateInXml = polyline.getRestorableState();
        polyline = new Polyline();
        polyline.restoreState(stateInXml);

        Polyline expected = new Polyline();
        assignExampleValues(expected);

        assertEquals(expected, polyline);
    }
