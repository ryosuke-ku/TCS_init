    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagValue other = (TagValue) obj;
        if (tagValues == null) {
            if (other.tagValues != null)
                return false;
        } else if (!tagValues.equals(other.tagValues))
            return false;
        return true;
    }
