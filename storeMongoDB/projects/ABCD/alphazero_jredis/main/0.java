    public static final byte[] compress(byte[] data){
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	try {
            GZIPOutputStream gzipOutputtStream = new GZIPOutputStream(out);
            gzipOutputtStream.write(data);
            gzipOutputtStream.close();
        }
        catch (IOException e) {
			throw new RuntimeException("Failed to GZip compress data", e);
        }
        return out.toByteArray();
    }
