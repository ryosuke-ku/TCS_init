    public void test_ENGINE_by_id_TestEngine() throws Exception {
        loadTestEngine();

        long engine = NativeCrypto.ENGINE_by_id(TEST_ENGINE_ID);
        assertTrue(engine != 0);
        NativeCrypto.ENGINE_add(engine);

        long pkey = NULL;
        try {
            final String rsaPem =
                      "-----BEGIN RSA PRIVATE KEY-----\n"
                    + "MIICXAIBAAKBgQCvvsYz1VKhU9PT0NHlotX22tcCjeaiVFNg0JrkjoK2XuMb+7a6\n"
                    + "R5bzgIr24+OnBB0LqgaKnHwxZTA73lo/Wy/Ms5Kvg4yX9UMkNE+PvH5vzcQBbFdI\n"
                    + "lwETFPvFokHO5OyOcEY+iVWG2fDloteH2JsrKYLh9Sx3Br5pHFCCm5qT5wIDAQAB\n"
                    + "AoGAWDxoNs371pPH3qkROUIwOuhU2ytziDzeP9V8bxQ9/GJXlE0kyRH4b/kxzBNO\n"
                    + "0SP3kUukTSOUFxi+xtA0b2rQ7Be2txtjzW1TGOHSCWbFrJAdTqeBcmQJSaZay8n1\n"
                    + "LOpk4/zvBl7VScBth1IgXP44v6lOzthsrDhMlUYs07ymwYECQQDonaLOhkmVThPa\n"
                    + "CIThdE5CN/wF5UDzGOz+ZBz3dt8D8QQMu0aZaPzibq9BC462j/fWeWS5OFzbq2+T\n"
                    + "+cor3nwPAkEAwWmTQdra6GMPEc40zNsM5ehF2FjOpX8aU8267eG56y0Y+GbHx2BN\n"
                    + "zAHfPxGBBH8cZ0cLhk4RSo/po7Vv+cRyqQJAAQz1N0mT+4Cmxk1TjFEiKVpnYP9w\n"
                    + "E6kBKQT6vINk7negNQ6Dex3mRn+Jexm6Q0jTLbzOn6eJg9R6ZIi0SQ5wMQJAKX2n\n"
                    + "fGohqdaORgiRZRzcsHlaemXatsAEetPYdO2Gf7/l6mvKEahEKC6CoLn1jmxiQHmK\n"
                    + "LF6U8QTcXyUuB0uwOQJBAIwWWjQGGc2sAQ1HW0C2wwCQbWneeBkiRBedonDBHtiB\n"
                    + "Wz0zS2CMCtBPNeHQmmsXH2Ca+ADdh53sKTuperLiuiw=\n"
                    + "-----END RSA PRIVATE KEY-----";
            pkey = NativeCrypto.ENGINE_load_private_key(engine, rsaPem);
            assertTrue(pkey != 0);
        } finally {
            if (pkey != NULL) {
                NativeCrypto.EVP_PKEY_free(pkey);
            }

            NativeCrypto.ENGINE_free(engine);
            NativeCrypto.ENGINE_finish(engine);
        }
    }
