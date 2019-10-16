    public void testEmptyStateDocument_Polyline()
    {
        Polyline polyline = new Polyline();
        assignExampleValues(polyline);

        String emptyStateInXml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<emptyDocumentRoot/>";
        polyline.restoreState(emptyStateInXml);

        // No attributes should have changed.
        Polyline expected = new Polyline();
        assignExampleValues(expected);

        assertEquals(expected, polyline);
    }
