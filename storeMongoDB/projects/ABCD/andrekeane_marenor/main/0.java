    public Dimension compute(int rectWidth, int rectHeight, int containerWidth, int containerHeight)
    {
        if (rectWidth < 0)
        {
            String message = Logging.getMessage("generic.InvalidWidth", rectWidth);
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (rectHeight < 0)
        {
            String message = Logging.getMessage("generic.InvalidHeight", rectHeight);
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (containerWidth < 0)
        {
            String message = Logging.getMessage("generic.InvalidWidth", containerWidth);
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (containerHeight < 0)
        {
            String message = Logging.getMessage("generic.InvalidHeight", containerHeight);
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        double aspectRatio;
        if (rectHeight != 0)
            aspectRatio = (double) rectWidth / rectHeight;
        else
            aspectRatio = 0;

        String xMode = this.getWidthMode();
        String yMode = this.getHeightMode();

        double width, height;

        if (NATIVE_DIMENSION.equals(xMode) && NATIVE_DIMENSION.equals(yMode)
            || NATIVE_DIMENSION.equals(xMode) && MAINTAIN_ASPECT_RATIO.equals(yMode)
            || MAINTAIN_ASPECT_RATIO.equals(xMode) && NATIVE_DIMENSION.equals(yMode)
            || MAINTAIN_ASPECT_RATIO.equals(xMode) && MAINTAIN_ASPECT_RATIO.equals(yMode))
        {
            // Keep original dimensions
            width = rectWidth;
            height = rectHeight;
        }
        else if (MAINTAIN_ASPECT_RATIO.equals(xMode))
        {
            // y dimension is specified, scale x to maintain aspect ratio
            height = computeSize(this.heightParam, this.heightUnits, containerHeight);
            width = height * aspectRatio;
        }
        else if (MAINTAIN_ASPECT_RATIO.equals(yMode))
        {
            // x dimension is specified, scale y to maintain aspect ratio
            width = computeSize(this.widthParam, this.widthUnits, containerWidth);
            if (aspectRatio != 0)
                height = width / aspectRatio;
            else
                height = 0;
        }
        else
        {
            if (NATIVE_DIMENSION.equals(xMode))
                width = rectWidth;
            else
                width = computeSize(this.widthParam, this.widthUnits, containerWidth);

            if (NATIVE_DIMENSION.equals(yMode))
                height = rectHeight;
            else
                height = computeSize(this.heightParam, this.heightUnits, containerHeight);
        }

        return new Dimension((int) width, (int) height);
    }
