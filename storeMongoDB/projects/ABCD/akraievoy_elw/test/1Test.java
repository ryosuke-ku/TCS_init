    public void testReadRespList() throws IOException {
        final ResponseList respList = RunnableLoadTask.readRespList(
                new ByteArrayInputStream(RESP_LIST_JSON.getBytes("UTF-8"))
        );
        assertNotNull(respList);
        assertEquals("gknu3opt", respList.getData().get("code").get("s")[1]);
    }
