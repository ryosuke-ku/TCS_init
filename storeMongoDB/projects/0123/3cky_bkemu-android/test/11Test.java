    public void testFloppyController() throws Exception {
        // Check drives are positioned to track 0
        for (FloppyDriveIdentifier driveIdentifier : FloppyDriveIdentifier.values()) {
            FloppyDrive drive = floppyController.getFloppyDrive(driveIdentifier);
            assertNotNull(drive);
            assertEquals(0, drive.getCurrentTrackNumber());
            assertEquals(FloppyDriveSide.DOWN, drive.getCurrentTrackSide());
        }
        // Check drive head positioning
        FloppyDrive drive = floppyController.getFloppyDrive(FloppyDriveIdentifier.A);
        assertEquals(0, drive.getNextTrackNumber(false));
        assertEquals(1, drive.getNextTrackNumber(true));
        drive.setCurrentTrack(FloppyController.TRACKS_PER_DISK - 1, FloppyDriveSide.DOWN);
        assertEquals(FloppyController.TRACKS_PER_DISK - 2, drive.getNextTrackNumber(false));
        assertEquals(FloppyController.TRACKS_PER_DISK - 1, drive.getNextTrackNumber(true));
        drive.setCurrentTrack(0, FloppyDriveSide.DOWN);
        // Check unmounted track data reading
        for (int position = 0; position < FloppyController.WORDS_PER_TRACK; position++) {
            assertEquals(0, drive.readCurrentTrackData(position));
        }
        // Mount disk image file
        byte[] testDiskImageData = mountTestDiskImage();
        int testDiskImageDataIndex = 0;
        // Check mounted disk image data reading
        for (int trackNumber = 0; trackNumber < FloppyController.TRACKS_PER_DISK; trackNumber++) {
            for (FloppyDriveSide trackSide : FloppyDriveSide.values()) {
                drive.setCurrentTrack(trackNumber, trackSide);
                int trackPosition = 0;
                int wordsToCheck = FloppyDriveTrackSequence.SEQ_GAP1_LENGTH;
                // Check GAP1
                while (wordsToCheck-- > 0) {
                    assertEquals("GAP1 position: " + trackPosition, FloppyDriveTrackSequence.SEQ_GAP,
                            drive.readCurrentTrackData(trackPosition++));
                }
                // Check sectors
                for (int sectorNumber = 1; sectorNumber <= FloppyController.SECTORS_PER_TRACK; sectorNumber++) {
                    // Check header sync
                    wordsToCheck = FloppyDriveTrackSequence.SEQ_SYNC_LENGTH;
                    while (wordsToCheck-- > 0) {
                        assertEquals("header sync position: " + trackPosition, FloppyDriveTrackSequence.SEQ_SYNC,
                                drive.readCurrentTrackData(trackPosition++));
                    }
                    // Check IDAM
                    assertTrue("IDAM position: " + trackPosition,
                            drive.isCurrentTrackDataMarkerPosition(trackPosition));
                    assertEquals("IDAM word 1 position: " + trackPosition, 0xa1a1,
                            drive.readCurrentTrackData(trackPosition++));
                    assertEquals("IDAM word 2 position: " + trackPosition, 0xa1fe,
                            drive.readCurrentTrackData(trackPosition++));
                    // Check head / track numbers
                    assertEquals("head/track numbers position: " + trackPosition,
                            (drive.getCurrentTrackNumber() << 8) | drive.getCurrentTrackSide().ordinal(),
                            drive.readCurrentTrackData(trackPosition++));
                    // Check sector number / sector size
                    assertEquals("sector number / size position: " + trackPosition,
                            (sectorNumber << 8) | 2,
                            drive.readCurrentTrackData(trackPosition++));
                    // Check sector head CRC
                    assertTrue("sector header CRC position: " + trackPosition,
                            drive.isCurrentTrackDataCrcPosition(trackPosition));
                    assertEquals("sector header CRC position: " + trackPosition,
                            Crc16.calculate(new byte[] {
                                (byte) 0xa1, (byte) 0xa1, (byte) 0xa1, (byte) 0xfe,
                                (byte) drive.getCurrentTrackNumber(),
                                (byte) drive.getCurrentTrackSide().ordinal(),
                                (byte) sectorNumber, 2 }) & 0177777,
                        drive.readCurrentTrackData(trackPosition++) & 0177777);
                    // Check GAP2
                    wordsToCheck = FloppyDriveTrackSequence.SEQ_GAP2_LENGTH;
                    while (wordsToCheck-- > 0) {
                        assertEquals("GAP2 position: " + trackPosition,
                                FloppyDriveTrackSequence.SEQ_GAP,
                                drive.readCurrentTrackData(trackPosition++));
                    }
                    // Check data sync
                    wordsToCheck = FloppyDriveTrackSequence.SEQ_SYNC_LENGTH;
                    while (wordsToCheck-- > 0) {
                        assertEquals("data sync position: " + trackPosition,
                                FloppyDriveTrackSequence.SEQ_SYNC,
                                drive.readCurrentTrackData(trackPosition++));
                    }
                    // Check DATA AM
                    assertTrue("DATA AM position: " + trackPosition,
                            drive.isCurrentTrackDataMarkerPosition(trackPosition));
                    assertEquals("DATA AM word 1 position: " + trackPosition, 0xa1a1,
                            drive.readCurrentTrackData(trackPosition++));
                    assertEquals("DATA AM word 2 position: " + trackPosition, 0xa1fb,
                            drive.readCurrentTrackData(trackPosition++));
                    // Check data
                    short crcValue = Crc16.calculate(new byte[] { (byte) 0xa1, (byte) 0xa1,
                            (byte) 0xa1, (byte) 0xfb });
                    wordsToCheck = FloppyController.BYTES_PER_SECTOR >> 1;
                    while (wordsToCheck-- > 0) {
                        byte dataByte1 = testDiskImageData[testDiskImageDataIndex++];
                        byte dataByte2 = testDiskImageData[testDiskImageDataIndex++];
                        assertEquals("Sector data position: " + trackPosition +
                                ", image index: " + (testDiskImageDataIndex - 2),
                                ((dataByte1 << 8) & 0177400) | (dataByte2 & 0377),
                                drive.readCurrentTrackData(trackPosition++));
                        crcValue = Crc16.calculate(crcValue, dataByte1);
                        crcValue = Crc16.calculate(crcValue, dataByte2);
                    }
                    // Check sector data CRC
                    assertTrue("sector data CRC position: " + trackPosition,
                            drive.isCurrentTrackDataCrcPosition(trackPosition));
                    assertEquals("sector data CRC", crcValue & 0177777,
                            drive.readCurrentTrackData(trackPosition++) & 0177777);
                    // Check GAP3/GAP4B
                    wordsToCheck = (sectorNumber < FloppyController.SECTORS_PER_TRACK)
                            ?  FloppyDriveTrackSequence.SEQ_GAP3_LENGTH
                                    : (FloppyController.WORDS_PER_TRACK - trackPosition );
                    while (wordsToCheck-- > 0) {
                        assertEquals("GAP3/GAP4B position: " + trackPosition,
                                FloppyDriveTrackSequence.SEQ_GAP,
                                drive.readCurrentTrackData(trackPosition++));
                    }
                }
            }
        }
    }
