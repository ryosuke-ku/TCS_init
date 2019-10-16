    public void sltu(final InstructionContext ctx) {
        if (u32(ctx.getS()) < u32(ctx.getT())) {
            ctx.setD(1);
        } else {
            ctx.setD(0);
        }
        ctx.advPc();
    }
