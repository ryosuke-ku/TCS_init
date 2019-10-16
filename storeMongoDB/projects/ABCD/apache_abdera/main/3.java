    public boolean contains(QName qname, QName attribute) {
        synchronized (attributes) {
            if (attributes.containsKey(qname)) {
                List<QName> attrs = attributes.get(qname);
                return attrs.contains(attribute);
            } else {
                return false;
            }
        }
    }
