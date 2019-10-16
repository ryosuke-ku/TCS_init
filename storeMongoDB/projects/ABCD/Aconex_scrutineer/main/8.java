    ElasticSearchIdAndVersionStream createElasticSearchIdAndVersionStream(ScrutineerCommandLineOptions options) {
        this.node = new NodeFactory().createNode(options);
        this.client = node.client();
        return new ElasticSearchIdAndVersionStream(new ElasticSearchDownloader(client, options.indexName, options.query, idAndVersionFactory), new ElasticSearchSorter(createSorter()), new IteratorFactory(idAndVersionFactory), SystemUtils.getJavaIoTmpDir().getAbsolutePath());
    }
