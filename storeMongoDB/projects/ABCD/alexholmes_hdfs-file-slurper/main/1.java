  private long hdfsFileCRC32(Path path) throws IOException {
    InputStream in = null;
    CRC32 crc = new CRC32();
    try {
      InputStream is = new BufferedInputStream(path.getFileSystem(config.getConfig()).open(path));
      if (config.getCodec() != null) {
        is = config.getCodec().createInputStream(is);
      }
      in = new CheckedInputStream(is, crc);
      org.apache.commons.io.IOUtils.copy(in, new NullOutputStream());
    } finally {
      org.apache.commons.io.IOUtils.closeQuietly(in);
    }
    return crc.getValue();
  }
