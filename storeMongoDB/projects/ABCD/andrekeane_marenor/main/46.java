    public void setImageSource(Object imageSource)
    {
        this.previousBackgroundTexture = this.backgroundTexture;
        this.backgroundTexture = null;

        if (imageSource != null)
        {
            this.backgroundTexture = new BasicWWTexture(imageSource, true);
        }
    }
