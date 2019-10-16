    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // qnames field
        final int qnamesSize = in.readInt();
        qnames = new ArrayList<QName>(qnamesSize);
        for (int i = 0; i < qnamesSize; i++) {
            qnames.add((QName)in.readObject());
        }

        // attributes field
        final int attributesSize = in.readInt();
        attributes = new HashMap<QName, List<QName>>(attributesSize);
        for (int i = 0; i < attributesSize; i++) {
            final QName k = (QName)in.readObject();
            final int vSize = in.readInt();
            final List<QName> v = new ArrayList<QName>(vSize);
            for (int j = 0; j < vSize; j++) {
                v.add((QName)in.readObject());
            }
            attributes.put(k, v);
        }
    }
