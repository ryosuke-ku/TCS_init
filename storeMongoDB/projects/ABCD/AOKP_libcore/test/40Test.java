    public void test_RAND_bytes_Success() throws Exception {
        byte[] output = new byte[128];
        NativeCrypto.RAND_bytes(output);

        boolean isZero = true;
        for (int i = 0; i < output.length; i++) {
            isZero &= (output[i] == 0);
        }

        assertFalse("Random output was zero. This is a very low probability event (1 in 2^128) "
                + "and probably indicates an error.", isZero);
    }
