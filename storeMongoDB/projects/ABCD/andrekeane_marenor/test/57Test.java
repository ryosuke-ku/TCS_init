    private static void assignExampleValues(AnnotationAttributes attrib)
    {
        attrib.setFrameShape(AVKey.SHAPE_ELLIPSE);
        attrib.setHighlighted(true);
        attrib.setHighlightScale(2.5);
        attrib.setSize(new java.awt.Dimension(255, 255));
        attrib.setScale(3.5);
        attrib.setOpacity(0.5);
        attrib.setLeader(AVKey.SHAPE_NONE);
        attrib.setLeaderGapWidth(100);
        attrib.setCornerRadius(4);
        attrib.setAdjustWidthToText(AVKey.SIZE_FIXED);
        attrib.setDrawOffset(new java.awt.Point(-3, -3));
        attrib.setInsets(new java.awt.Insets(11, 11, 11, 11));
        attrib.setBorderWidth(5.5);
        attrib.setBorderStippleFactor(6);
        attrib.setBorderStipplePattern((short) 0xFC0C);
        attrib.setAntiAliasHint(Annotation.ANTIALIAS_NICEST);
        attrib.setVisible(false);
        attrib.setFont(java.awt.Font.decode("Arial-ITALIC-24"));
        attrib.setTextAlign(AVKey.CENTER);
        attrib.setTextColor(java.awt.Color.PINK);
        attrib.setBackgroundColor(java.awt.Color.MAGENTA);
        attrib.setBorderColor(java.awt.Color.CYAN);
        attrib.setImageSource("path/to/image.ext");
        attrib.setImageScale(7.5);
        attrib.setImageOffset(new java.awt.Point(-4, -4));
        attrib.setImageOpacity(0.4);
        attrib.setImageRepeat(AVKey.REPEAT_Y);
        attrib.setDistanceMaxScale(0.1);
        attrib.setDistanceMaxScale(8.5);
        attrib.setEffect(AVKey.TEXT_EFFECT_OUTLINE);
    }
