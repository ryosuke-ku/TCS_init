    public Long getSize()
    {
        Object sz = m_map.get(Resource.SIZE);
        if (sz instanceof Long)
            return ((Long) sz);

        long size = findResourceSize();
        m_map.put(Resource.SIZE, size);
        return size;
    }
