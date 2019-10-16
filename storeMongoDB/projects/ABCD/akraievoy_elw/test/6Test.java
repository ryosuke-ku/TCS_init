    public void testSltu_posneg_true() {
        registers.setReg(Reg.t1, 0);
        registers.setReg(Reg.t2, -1);
        registers.setReg(Reg.t3, 2);

        assembleInstruction("sltu $t3, $t1, $t2");

        alu.sltu(iCtx);

        assertEquals(0, registers.getReg(Reg.t1));
        assertEquals(-1, registers.getReg(Reg.t2));
        assertEquals(1, registers.getReg(Reg.t3));
    }
