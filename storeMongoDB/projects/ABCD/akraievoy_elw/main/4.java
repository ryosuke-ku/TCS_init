    public void or(final InstructionContext ctx) {
        ctx.setD(ctx.getS() | ctx.getT());
        ctx.advPc();
    }
