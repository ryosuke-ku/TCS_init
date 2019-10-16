    private void index(Event e, XContentBuilder builder) {
        String iName = indexName;
        if (indexPattern != null) {
            iName = e.escapeString(indexPattern);
        }
        client.prepareIndex(iName, indexType, null)
                .setSource(builder)
                .execute()
                .actionGet();

        if (!iName.equals(indexName)) {
            client.admin().indices().prepareAliases().addAlias(iName, indexName).execute().actionGet();
        }
    }
