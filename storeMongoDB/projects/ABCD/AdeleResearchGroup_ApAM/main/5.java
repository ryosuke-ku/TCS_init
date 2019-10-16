    public boolean isInterfaceImplemented(String itf) {
        for (int i = 0; i < m_interfaces.length; i++) {
            if (m_interfaces[i].equals(itf)) { return true; }
        }
        return false;
    }
