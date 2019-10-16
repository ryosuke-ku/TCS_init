  public static void testEncryption(final Configuration conf, final String cipher,
      byte[] key) throws IOException {
    if (cipher == null) {
      return;
    }
    testKeyProvider(conf);
    testCipherProvider(conf);
    Boolean result = cipherResults.get(cipher);
    if (result == null) {
      try {
        Encryption.Context context = Encryption.newContext(conf);
        context.setCipher(Encryption.getCipher(conf, cipher));
        if (key == null) {
          // Make a random key since one was not provided
          context.setKey(context.getCipher().getRandomKey());
        } else {
          // This will be a wrapped key from schema
          context.setKey(EncryptionUtil.unwrapKey(conf,
            conf.get(HConstants.CRYPTO_MASTERKEY_NAME_CONF_KEY, "hbase"),
            key));
        }
        byte[] iv = null;
        if (context.getCipher().getIvLength() > 0) {
          iv = new byte[context.getCipher().getIvLength()];
          Bytes.random(iv);
        }
        byte[] plaintext = new byte[1024];
        Bytes.random(plaintext);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encryption.encrypt(out, new ByteArrayInputStream(plaintext), context, iv);
        byte[] ciphertext = out.toByteArray();
        out.reset();
        Encryption.decrypt(out, new ByteArrayInputStream(ciphertext), plaintext.length,
          context, iv);
        byte[] test = out.toByteArray();
        if (!Bytes.equals(plaintext, test)) {
          throw new IOException("Did not pass encrypt/decrypt test");
        }
        cipherResults.put(cipher, true);
      } catch (Exception e) {
        cipherResults.put(cipher, false);
        throw new IOException("Cipher " + cipher + " failed test: " + e.getMessage(), e);
      }
    } else if (result.booleanValue() == false) {
      throw new IOException("Cipher " + cipher + " previously failed test");
    }
  }
