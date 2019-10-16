    public void testSize()
    {
        // Test with native width and fractional height
        Size size = new Size(Size.NATIVE_DIMENSION, 0, AVKey.PIXELS, Size.EXPLICIT_DIMENSION, 0.5, AVKey.FRACTION);
        Dimension dim = size.compute(70, 10, 100, 100);
        assertTrue("Dimension should be 70 x 50", dim.equals(new Dimension(70, 50)));

        // Test with maintain aspect ratio
        size = new Size(Size.MAINTAIN_ASPECT_RATIO, 0, AVKey.PIXELS, Size.EXPLICIT_DIMENSION, 50, AVKey.PIXELS);
        dim = size.compute(20, 10, 100, 100);
        assertTrue("Dimension should be 100 x 50", dim.equals(new Dimension(100, 50)));
    }
