    private void test_HprofData_binary(HprofData hprofData, boolean strict) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryHprofWriter.write(hprofData, out);
        out.close();

        byte[] bytes = out.toByteArray();
        assertFalse(bytes.length == 0);
        if (false) {
            File file = new File("/sdcard/java.hprof");
            OutputStream debug = new FileOutputStream(file);
            debug.write(bytes);
            debug.close();
            System.out.println("Wrote binary hprof data to " + file);
        }

        InputStream in = new ByteArrayInputStream(bytes);
        BinaryHprofReader reader = new BinaryHprofReader(in);
        assertTrue(reader.getStrict());
        reader.read();
        in.close();
        assertEquals("JAVA PROFILE 1.0.2", reader.getVersion());
        assertNotNull(reader.getHprofData());

        HprofData parsed = reader.getHprofData();
        assertHprofData(hprofData, strict);

        assertEquals(Long.toHexString(hprofData.getStartMillis()),
                     Long.toHexString(parsed.getStartMillis()));
        assertEquals(Long.toHexString(hprofData.getFlags()),
                     Long.toHexString(parsed.getFlags()));
        assertEquals(Long.toHexString(hprofData.getDepth()),
                     Long.toHexString(parsed.getDepth()));
        assertEquals(hprofData.getThreadHistory(),
                     parsed.getThreadHistory());
        assertEquals(hprofData.getSamples(),
                     parsed.getSamples());
    }
