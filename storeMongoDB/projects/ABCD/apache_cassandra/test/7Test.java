    private static boolean mapAppliesTo(ColumnCondition.CollectionBound bound, Map<ByteBuffer, ByteBuffer> conditionValues, Map<ByteBuffer, ByteBuffer> columnValues)
    {
        CFMetaData cfm = CFMetaData.compile("create table foo(a int PRIMARY KEY, b map<int, int>)", "ks");
        Map<ByteBuffer, CollectionType> typeMap = new HashMap<>();
        typeMap.put(ByteBufferUtil.bytes("b"), MapType.getInstance(Int32Type.instance, Int32Type.instance, true));
        ColumnDefinition definition = ColumnDefinition.regularDef(cfm, ByteBufferUtil.bytes("b"), MapType.getInstance(Int32Type.instance, Int32Type.instance, true));

        List<Cell> cells = new ArrayList<>(columnValues.size());
        if (columnValues != null)
        {
            for (Map.Entry<ByteBuffer, ByteBuffer> entry : columnValues.entrySet())
                cells.add(testCell(definition, entry.getValue(), CellPath.create(entry.getKey())));
        }

        return bound.mapAppliesTo(MapType.getInstance(Int32Type.instance, Int32Type.instance, true), cells.iterator(), conditionValues, bound.operator);
    }
