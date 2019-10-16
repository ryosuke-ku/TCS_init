    public void put(String key, String value, String type)
    {
        key = key.toLowerCase();
        m_hash = 0;
        if (Property.URI.equals(type) || URI.equals(key))
        {
            if (m_uris == null)
            {
                m_uris = new HashMap();
            }
            m_uris.put(key, value);
        }
        else if (Property.VERSION.equals(type) || VERSION.equals(key))
        {
            m_map.put(key, VersionTable.getVersion(value));
        }
        else if (Property.LONG.equals(type) || SIZE.equals(key))
        {
            m_map.put(key, Long.valueOf(value));
        }
        else if (Property.SET.equals(type) || CATEGORY.equals(key))
        {
            StringTokenizer st = new StringTokenizer(value, ",");
            Set s = new HashSet();
            while (st.hasMoreTokens())
            {
                s.add(st.nextToken().trim());
            }
            m_map.put(key, s);
        }
        else
        {
            m_map.put(key, value);
        }
    }
