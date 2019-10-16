    public boolean remove(Object object)
    {
        // Check if exits
        int i = getIndex(object);
        if (i < 0)
            return false; // Element not found
        // remove
        list.remove(i);
        return true;
    }
