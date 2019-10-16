    public void customizeDocumentAfterNewLine(IDocument doc, final DocumentCommand c) {
        int offset = c.offset;
        if (offset == -1 || doc.getLength() == 0) {
            return;
        }

        final StringBuilder buf = new StringBuilder(c.text);
        try {
            // find start of line
            IRegion line = doc.getLineInformationOfOffset(c.offset);

            IRegion prefix = findPrefixRange(doc, line);
            String indentation = doc.get(prefix.getOffset(), prefix.getLength());

            if (shouldCloseMultiline(doc, c.offset)) {
                try {
                    doc.replace(c.offset, 0, indentation + " " + MULTILINE_END); // close the comment in order to parse //$NON-NLS-1$

                    // as we are auto-closing, the comment becomes eligible for
                    // auto-doc'ing
                    IASTDeclaration dec = null;
                    IASTTranslationUnit ast = getAST();

                    if (ast != null) {
                        dec = findFollowingDeclaration(ast, offset);
                        if (dec == null) {
                            IASTNodeSelector ans = ast.getNodeSelector(ast.getFilePath());
                            IASTNode node = ans.findEnclosingNode(offset, 0);
                            if (node instanceof IASTDeclaration) {
                                dec = (IASTDeclaration) node;
                            }
                        }
                    }

                    if (dec != null) {
                        ITypedRegion partition = TextUtilities.getPartition(doc,
                                ICPartitions.C_PARTITIONING /* this! */, offset, false);
                        StringBuilder content = customizeAfterNewLineForDeclaration(doc, dec, partition);
                        buf.append(indent(content, indentation + MULTILINE_MID));
                    }

                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
                c.shiftsCaret = false;
                c.text = buf.toString();
                int newOffset = offset(c.text, new String[] { "@abstract", "@description" }); //$NON-NLS-1$ //$NON-NLS-2$
                if (newOffset == -1) {
                    newOffset = indentation.length();
                }
                c.caretOffset = c.offset + newOffset;
            } else {
                // buf.append('\n');
                buf.append(indentation);
                if (!indentation.endsWith(MULTILINE_MID)) {
                    buf.append(MULTILINE_MID);
                }
                c.text = buf.toString();
            }

        } catch (BadLocationException excp) {
            // stop work
        }
    }
