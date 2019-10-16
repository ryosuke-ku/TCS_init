    private boolean execute(int address) {
        boolean isSuccess = false;
        Cpu cpu = computer.getCpu();
        int initialAddress = cpu.readRegister(false, Cpu.PC);
        assertTrue("can't push PC to stack", cpu.push(initialAddress));
        cpu.writeRegister(false, Cpu.PC, address);
        int cpuOps = MAX_CPU_OPS;
        while (cpuOps-- > 0) {
            try {
                cpu.executeNextOperation();
            } catch (Exception e) {
                e.printStackTrace();
                fail("can't execute operation, PC: " + Integer.toOctalString(cpu
                        .readRegister(false, Cpu.PC)));
            }
            if (initialAddress == cpu.readRegister(false, Cpu.PC)) {
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }
