    public boolean equals(Object o) {
        if (!(o instanceof Uri)) {
            return false;
        }

        Uri other = (Uri) o;

        return toString().equals(other.toString());
    }
