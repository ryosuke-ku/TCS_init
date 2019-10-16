    public void save(PasswordDatabase database) throws IOException, CryptoException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        // Flatpack the database revision and options
        database.getRevisionObj().increment();
        database.getRevisionObj().flatPack(os);
        database.getDbOptions().flatPack(os);

        // Flatpack the accounts
        Iterator it = database.getAccountsHash().values().iterator();
        while (it.hasNext()) {
            AccountInformation ai = (AccountInformation) it.next();
            ai.flatPack(os);
        }
        os.close();
        byte[] dataToEncrypt = os.toByteArray();

        //Now encrypt the database data
        byte[] encryptedData = encryptionService.encrypt(dataToEncrypt);
        
        //Write the salt and the encrypted data out to the database file
        FileOutputStream fos = new FileOutputStream(database.getDatabaseFile());
        fos.write(FILE_HEADER.getBytes());
        fos.write(DB_VERSION);
        fos.write(encryptionService.getSalt());
        fos.write(encryptedData);
        fos.close();
    }
