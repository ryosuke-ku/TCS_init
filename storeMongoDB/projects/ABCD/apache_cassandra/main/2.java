    public static ColumnIdentifier getInterned(AbstractType<?> type, ByteBuffer bytes, String text)
    {
        InternedKey key = new InternedKey(type, bytes);
        ColumnIdentifier id = internedInstances.get(key);
        if (id != null)
            return id;

        ColumnIdentifier created = new ColumnIdentifier(bytes, text, true);
        ColumnIdentifier previous = internedInstances.putIfAbsent(key, created);
        return previous == null ? created : previous;
    }
