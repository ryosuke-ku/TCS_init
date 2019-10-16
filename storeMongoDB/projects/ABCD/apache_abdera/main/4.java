    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // qnames field
        assert qnames != null;
        out.writeInt(qnames.size());
        for (QName q : qnames) {
            out.writeObject(q);
        }

        // attributes field
        assert attributes != null;
        out.writeInt(attributes.size());
        for (Map.Entry<QName, List<QName>> e : attributes.entrySet()) {
            out.writeObject(e.getKey());
            final List<QName> v = e.getValue();
            assert v != null;
            out.writeInt(v.size());
            for (QName q : v) {
                out.writeObject(q);
            }
        }
    }
