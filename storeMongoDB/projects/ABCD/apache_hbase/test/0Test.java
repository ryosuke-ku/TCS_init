  private void nativeCodecTest(String codecName, String libName, String codecClassName) {
    if (isCompressionAvailable(codecClassName)) {
      try {
        if (libName != null) {
          System.loadLibrary(libName);
        }

        try {
            Configuration conf = new Configuration();
            CompressionCodec codec = (CompressionCodec)
              ReflectionUtils.newInstance(conf.getClassByName(codecClassName), conf);

            DataOutputBuffer compressedDataBuffer = new DataOutputBuffer();
            CompressionOutputStream deflateFilter = codec.createOutputStream(compressedDataBuffer);

            byte[] data = new byte[1024];
            DataOutputStream deflateOut = new DataOutputStream(new BufferedOutputStream(deflateFilter));
            deflateOut.write(data, 0, data.length);
            deflateOut.flush();
            deflateFilter.finish();

            // Codec class, codec nativelib and Hadoop nativelib with codec JNIs are present
            assertTrue(CompressionTest.testCompression(codecName));
        } catch (UnsatisfiedLinkError e) {
          // Hadoop nativelib does not have codec JNIs.
          // cannot assert the codec here because the current logic of
          // CompressionTest checks only classloading, not the codec
          // usage.
          LOG.debug("No JNI for codec '" + codecName + "' " + e.getMessage());
        } catch (Exception e) {
          LOG.error(codecName, e);
        }
      } catch (UnsatisfiedLinkError e) {
        // nativelib is not available
        LOG.debug("Native lib not available: " + codecName);
        assertFalse(CompressionTest.testCompression(codecName));
      }
    } else {
      // Compression Codec class is not available
      LOG.debug("Codec class not available: " + codecName);
      assertFalse(CompressionTest.testCompression(codecName));
    }
  }
