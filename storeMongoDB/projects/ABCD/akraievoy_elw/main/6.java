    public void slt(final InstructionContext ctx) {
        if (ctx.getS() < ctx.getT()) {
            ctx.setD(1);
        } else {
            ctx.setD(0);
        }
        ctx.advPc();
    }
