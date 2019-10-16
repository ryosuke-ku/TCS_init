    public void testRestorableStateLegacy()
    {
        // Test with fractional dimensions
        Size input = new Size("MaintainAspectRatio", 0, null, "ExplicitDimension", 100, AVKey.PIXELS);
        Size expected = new Size(Size.MAINTAIN_ASPECT_RATIO, 0, null, Size.EXPLICIT_DIMENSION, 100, AVKey.PIXELS);

        RestorableSupport rs = RestorableSupport.newRestorableSupport();
        input.getRestorableState(rs, null);

        Size actual = new Size();
        actual.restoreState(rs, null);

        assertEquals(expected, actual);
    }
