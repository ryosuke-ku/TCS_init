    protected void assembleInstruction(final String instructionCode) {
        assembleInstructions(Arrays.asList(
                instructionCode,
                "noop",
                "label: noop"
        ));
    }
