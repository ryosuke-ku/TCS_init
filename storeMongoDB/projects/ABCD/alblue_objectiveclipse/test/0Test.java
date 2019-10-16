    private void executeCheck(String prefix, String postfix, String expected) throws BadLocationException {
        HDMAESTest a = new HDMAESTest(prefix, postfix);
        CommandTest c = new CommandTest();
        IDocument document = a.getDocument();
        c.text = "\n"; //$NON-NLS-1$
        c.length = 0;
        c.offset = a.prefix.length();
        c.caretOffset = -1;
        c.owner = null;
        c.doit = true;
        c.shiftsCaret = true;
        a.customizeDocumentAfterNewLine(document, c);
        document.replace(c.offset, c.length, c.text);
        assertEquals(expected, document.get());
    }
