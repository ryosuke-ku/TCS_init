    public void testFloppyControllerOperations() throws Exception {
//        floppyController.setDebugEnabled(true);
        // Mount disk image file
        byte[] testDiskImageData = mountTestDiskImage();
        Cpu cpu = computer.getCpu();
        // Initialize FDD
        cpu.writeRegister(false, Cpu.R3, FDD_BLOCK_START_ADDR);
        assertTrue("can't initialize FDD", execute(0160010));
        // Single sector read
        int dataIndex = 0;
        cpu.writeMemory(true, FDD_BLOCK_DRIVE_NUM, FloppyDriveIdentifier.A.ordinal()); // Select drive
        for (int blockNumber = 0; blockNumber < FloppyController.BYTES_PER_DISK
                / FloppyController.BYTES_PER_SECTOR; blockNumber++) {
            cpu.writeRegister(false, Cpu.R0, blockNumber); // Sector number
            cpu.writeRegister(false, Cpu.R1, 0400); // Data length
            cpu.writeRegister(false, Cpu.R2, 01000); // Data read address
            assertTrue("can't read sector " + blockNumber, execute(0160004));
            assertTrue("sector " + blockNumber + " read error " + computer.readMemory(false, 052),
                    !cpu.isPswFlagSet(Cpu.PSW_FLAG_C));
            // Check read data
            for (int address = 01000; address < 02000; address++) {
                assertEquals("sector " + blockNumber + " read error at address " +
                        Integer.toOctalString(address), testDiskImageData[dataIndex++] & 0377,
                        computer.readMemory(true, address));
            }
        }
        // Multisector read
        dataIndex = 0;
        cpu.writeRegister(false, Cpu.R0, 0); // Sector number
        cpu.writeRegister(false, Cpu.R1, 020000); // Data length
        cpu.writeRegister(false, Cpu.R2, 040000); // Data read address
        assertTrue("can't read block", execute(0160004));
        for (int address = 040000; address < 0100000; address++) {
            assertEquals("block read error at address " + Integer.toOctalString(address),
                    testDiskImageData[dataIndex++] & 0377,
                    computer.readMemory(true, address));
        }
    }
