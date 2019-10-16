    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        Size that = (Size) o;

        if (Double.compare(this.widthParam, that.widthParam) != 0)
            return false;
        if (Double.compare(this.heightParam, that.heightParam) != 0)
            return false;
        if (this.widthUnits != null ? !this.widthUnits.equals(that.widthUnits) : that.widthUnits != null)
            return false;
        if (this.heightUnits != null ? !this.heightUnits.equals(that.heightUnits) : that.heightUnits != null)
            return false;
        if (this.widthMode != null ? !this.widthMode.equals(that.widthMode) : that.widthMode != null)
            return false;
        //noinspection RedundantIfStatement
        if (this.heightMode != null ? !this.heightMode.equals(that.heightMode) : that.heightMode != null)
            return false;

        return true;
    }
