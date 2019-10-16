    private byte[] mountTestDiskImage() throws Exception {
        File testDiskImageFile = new File(TEST_DISK_IMAGE_FILE_NAME);
        byte[] testDiskImageData = FileUtils.readFileToByteArray(testDiskImageFile);
        floppyController.mountDiskImage(testDiskImageFile.toURI().toString(),
                FloppyDriveIdentifier.A, true);
        return testDiskImageData;
    }
