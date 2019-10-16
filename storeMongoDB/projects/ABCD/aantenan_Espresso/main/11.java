    public void visit(final SqlComparisonExpression<E> node) throws SQLException {
        codeStack.addFirst(new CodeSnippetList());
        super.visit(node);
        
        final CodeSnippetList snippets = codeStack.removeFirst();

        // Objects are handled as Comparable, regardless of their type
        final CodeSnippet lhs = snippets.getSnippetAt(0);
        if (lhs.getJvmType() == OBJECT)
            lhs.setClazz(Comparable.class);
        final CodeSnippet rhs = snippets.getSnippetAt(1);
        if (rhs.getJvmType() == OBJECT)
            rhs.setClazz(Comparable.class);
        final CodeSnippet comparison = new CodeSnippet(OBJECT, SqlComparisonOperator.class);

        // We will make a static call to EvaluatorHelper.evalBetween, figure out the signature
        // It will be something like (org/espresso/token/SqlComparisonOperator;)Z or
        // (Ljava/lang/Comparable;Ljava/lang/Comparable;Lorg/espresso/token/SqlComparisonOperator;)Z
        final String signature = buildSignature(BOOLEAN, lhs, rhs, comparison);

        // Add reference to the method to the pool gen and make the call
        final int enumIndex = constPoolGen.addFieldref(
                SqlComparisonOperator.class.getCanonicalName().replace('.', '/'),
                node.getRawOperator().name(), classToJvmStringType(SqlComparisonOperator.class));
        final int helperIndex = constPoolGen.addMethodref(EvaluatorHelper.class.getCanonicalName(),
                "evalCompare", signature);

        final CodeSnippet snippet = new CodeSnippet(BOOLEAN);
        snippet.append(lhs);
        snippet.append(rhs);
        snippet.append(new GETSTATIC(enumIndex));
        snippet.append(new INVOKESTATIC(helperIndex));

        // Top of stack now has a boolean
        final CodeSnippetList tos = codeStack.peekFirst();
        tos.append(snippet);
    }
