    public void shouldDownloadAndSortOnSetup() throws IOException {
        ElasticSearchIdAndVersionStream elasticSearchIdAndVersionStream = spy(new ElasticSearchIdAndVersionStream(elasticSearchDownloader, elasticSearchSorter, iteratorFactory, SystemUtils.getJavaIoTmpDir().getAbsolutePath()));
        doReturn(sortedOutputStream).when(elasticSearchIdAndVersionStream).createSortedOutputStream();
        doReturn(unSortedOutputStream).when(elasticSearchIdAndVersionStream).createUnsortedOutputStream();
        doReturn(unSortedInputStream).when(elasticSearchIdAndVersionStream).createUnSortedInputStream();
        elasticSearchIdAndVersionStream.open();
        verify(elasticSearchDownloader).downloadTo(unSortedOutputStream);
        verify(elasticSearchSorter).sort(unSortedInputStream,sortedOutputStream);
    }
