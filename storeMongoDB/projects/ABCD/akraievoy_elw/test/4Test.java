    protected void assembleInstructions(final List<String> intstructions) {
        final Instruction instruction = validator.assemble(new Result[1], intstructions)[0];
        iCtx.setInstruction(instruction);
    }
