    public Evaluator compile()
            throws SQLException {

        codeStack.add(new CodeSnippetList());
        root.accept(this);
        final InstructionList instructionList = codeStack.getFirst().asInstructionList();
        instructionList.append(new IRETURN());
        
        // Create the "matches" method matching the Evaluator.matches declaration
        final MethodGen methodGen = new MethodGen(ACC_PUBLIC,
                org.apache.bcel.generic.Type.BOOLEAN,
                new Type[]{org.apache.bcel.generic.Type.OBJECT},
                null, "matches", generatedClassName, instructionList, constPoolGen);
        methodGen.setMaxLocals();
        methodGen.setMaxStack();
        // Adds the method to the class
        classGen.addMethod(methodGen.getMethod());

        // Create the constructor
        classGen.addEmptyConstructor(ACC_PUBLIC);

        // Done, print the class definition so we can sanity check what we just did
        final JavaClass javaClass = classGen.getJavaClass();
        System.out.println(javaClass);
        for (final Method method : javaClass.getMethods())
            System.out.println(method.getCode().toString(true));

        // Get the bytecode representing the class
        final byte[] bytecode = javaClass.getBytes();

        // Create a special class loader that knows only how to do one thing: create an instance
        // of the class we just created. First, define the class, then create a new instance.
        // Since we know our class implements the Evaluator interface, we can safely cast it.
        try {
            return evaluatorLoader.getEvaluator(bytecode, generatedClassName);
        } catch (final Exception e) {
            throw new SQLException("Could not create compiled class", e);
        }
    }
