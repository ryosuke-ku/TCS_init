    private void assertSubtract(String ipa, String ipb, String res) {
        IPTarget a = new IPTarget(ipa);
        IPTarget b = null;
        
        if(ipb != null) {
            b = new IPTarget(ipb);
        }
        
        BigInteger rb = null;
        if(res != null) {
            rb = new BigInteger(res);
        }
        
        Assert.assertEquals("Unexpected result for '" + ipa + "'.subtract('" + ipb + "')", rb, a.subtract(b));
    }
