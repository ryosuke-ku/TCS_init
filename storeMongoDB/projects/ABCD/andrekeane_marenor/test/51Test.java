    private static void assertEquals(AnnotationAttributes expected, AnnotationAttributes actual)
    {
        assertNotNull("Expected is null", expected);
        assertNotNull("Acutal is null", actual);
        assertEquals("frameShape", expected.getFrameShape(), actual.getFrameShape());
        assertEquals("highlighted", expected.isHighlighted(), actual.isHighlighted());
        assertEquals("highlightScale", expected.getHighlightScale(), actual.getHighlightScale());
        assertEquals("size", expected.getSize(), actual.getSize());
        assertEquals("scale", expected.getScale(), actual.getScale());
        assertEquals("opacity", expected.getOpacity(), actual.getOpacity());
        assertEquals("leader", expected.getLeader(), actual.getLeader());
        assertEquals("leaderGapWidth", expected.getLeaderGapWidth(), actual.getLeaderGapWidth());
        assertEquals("cornerRadius", expected.getCornerRadius(), actual.getCornerRadius());
        assertEquals("adjustWidthToText", expected.getAdjustWidthToText(), actual.getAdjustWidthToText());
        assertEquals("drawOffset", expected.getDrawOffset(), actual.getDrawOffset());
        assertEquals("insets", expected.getInsets(), actual.getInsets());
        assertEquals("borderWidth", expected.getBorderWidth(), actual.getBorderWidth());
        assertEquals("borderStippleFactor", expected.getBorderStippleFactor(), actual.getBorderStippleFactor());
        assertEquals("borderStipplePattern", expected.getBorderStipplePattern(), actual.getBorderStipplePattern());
        assertEquals("antiAliasHint", expected.getAntiAliasHint(), actual.getAntiAliasHint());
        assertEquals("visible", expected.isVisible(), actual.isVisible());
        assertEquals("font", expected.getFont(), actual.getFont());
        assertEquals("textAlign", expected.getTextAlign(), actual.getTextAlign());
        assertEquals("textColor", expected.getTextColor(), actual.getTextColor());
        assertEquals("backgroundColor", expected.getBackgroundColor(), actual.getBackgroundColor());
        assertEquals("borderColor", expected.getBorderColor(), actual.getBorderColor());
        assertEquals("imageSource", expected.getImageSource(), actual.getImageSource());
        assertEquals("imageScale", expected.getImageScale(), actual.getImageScale());
        assertEquals("imageOffset", expected.getImageOffset(), actual.getImageOffset());
        assertEquals("imageOpacity", expected.getImageOpacity(), actual.getImageOpacity());
        assertEquals("imageRepeat", expected.getImageRepeat(), actual.getImageRepeat());
        assertEquals("distanceMinScale", expected.getDistanceMinScale(), actual.getDistanceMinScale());
        assertEquals("distanceMaxScale", expected.getDistanceMaxScale(), actual.getDistanceMaxScale());
        assertEquals("distanceMinOpacity", expected.getDistanceMinOpacity(), actual.getDistanceMinOpacity());
        assertEquals("effect", expected.getEffect(), actual.getEffect());
    }
