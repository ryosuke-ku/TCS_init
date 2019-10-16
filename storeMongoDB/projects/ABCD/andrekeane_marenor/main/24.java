    public void setHighlightColor(Color highlightColor)
    {
        if (highlightColor == null)
        {
            String message = Logging.getMessage("nullValue.ColorIsNull");
            Logging.logger().severe(message);
            throw new IllegalStateException(message);
        }

        this.highlightColor = highlightColor;
    }
