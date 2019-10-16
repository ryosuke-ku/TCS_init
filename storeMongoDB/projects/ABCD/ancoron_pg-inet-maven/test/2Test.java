    private void assertMergeResult(String res, IPNetwork merged) {
        if(res == null) {
            Assert.assertNull("Unexpectedly got a super-network", merged);
        } else {
            Assert.assertEquals("Invalid merged network", new IPNetwork(res).getValue(), merged.getValue());
        }
    }
