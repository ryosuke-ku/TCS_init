    public void addSQL(StringBuilder buf, long context)
    {
        if ((context & CTX_NAME) != 0)
            column.addSQL(buf, CTX_NAME);
        if ((context & CTX_NAME) != 0 && (context & CTX_VALUE) != 0)
            buf.append("=");
        if ((context & CTX_VALUE) != 0)
            buf.append(getObjectValue(column.getDataType(), value, context, "+"));
    }
