    private void check_EC_GROUP(int type, String name, String pStr, String aStr, String bStr,
            String xStr, String yStr, String nStr, long hLong) throws Exception {
        long group1 = NULL, group2 = NULL, point1 = NULL, point2 = NULL, key1 = NULL;
        try {
            group1 = NativeCrypto.EC_GROUP_new_by_curve_name(name);
            assertTrue(group1 != NULL);
            assertEquals(NativeCrypto.OBJ_txt2nid_longName(name),
                    NativeCrypto.EC_GROUP_get_curve_name(group1));
            assertEquals(type, NativeCrypto.get_EC_GROUP_type(group1));

            // prime
            BigInteger p = new BigInteger(pStr, 16);
            // first coefficient
            BigInteger a = new BigInteger(aStr, 16);
            // second coefficient
            BigInteger b = new BigInteger(bStr, 16);
            // x affine coordinate of generator
            BigInteger x = new BigInteger(xStr, 16);
            // y affine coordinate of generator
            BigInteger y = new BigInteger(yStr, 16);
            // order of the generator
            BigInteger n = new BigInteger(nStr, 16);
            // cofactor of generator
            BigInteger h = BigInteger.valueOf(hLong);

            group2 = NativeCrypto.EC_GROUP_new_curve(type, p.toByteArray(),
                    a.toByteArray(), b.toByteArray());
            assertEquals(type, NativeCrypto.get_EC_GROUP_type(group2));

            point2 = NativeCrypto.EC_POINT_new(group2);

            NativeCrypto.EC_POINT_set_affine_coordinates(group2, point2, x.toByteArray(),
                    y.toByteArray());

            NativeCrypto.EC_GROUP_set_generator(group2, point2, n.toByteArray(), h.toByteArray());

            point1 = NativeCrypto.EC_GROUP_get_generator(group2);
            assertTrue(NativeCrypto.EC_POINT_cmp(group1, point1, point2));

            byte[][] pab = NativeCrypto.EC_GROUP_get_curve(group2);
            assertEquals(3, pab.length);

            BigInteger p2 = new BigInteger(pab[0]);
            assertEquals(p, p2);

            BigInteger a2 = new BigInteger(pab[1]);
            assertEquals(a, a2);

            BigInteger b2 = new BigInteger(pab[2]);
            assertEquals(b, b2);

            byte[][] xy = NativeCrypto.EC_POINT_get_affine_coordinates(group2, point2);
            assertEquals(2, xy.length);

            BigInteger x2 = new BigInteger(xy[0]);
            assertEquals(x, x2);

            BigInteger y2 = new BigInteger(xy[1]);
            assertEquals(y, y2);

            BigInteger n2 = new BigInteger(NativeCrypto.EC_GROUP_get_order(group1));
            assertEquals(n, n2);

            BigInteger h2 = new BigInteger(NativeCrypto.EC_GROUP_get_cofactor(group2));
            assertEquals(h, h2);

            assertTrue(NativeCrypto.EC_GROUP_cmp(group1, group2));

            key1 = NativeCrypto.EC_KEY_generate_key(group1);
            long groupTmp = NativeCrypto.EC_KEY_get0_group(key1);
            assertEquals(NativeCrypto.EC_GROUP_get_curve_name(group1),
                    NativeCrypto.EC_GROUP_get_curve_name(groupTmp));

        } finally {
            if (group1 != NULL) {
                NativeCrypto.EC_GROUP_clear_free(group1);
            }

            if (group2 != NULL) {
                NativeCrypto.EC_GROUP_clear_free(group2);
            }

            if (point1 != NULL) {
                NativeCrypto.EC_POINT_clear_free(point1);
            }

            if (point2 != NULL) {
                NativeCrypto.EC_POINT_clear_free(point2);
            }

            if (key1 != NULL) {
                NativeCrypto.EVP_PKEY_free(key1);
            }
        }
    }
