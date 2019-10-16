    public void writeTreeWithOneChildAndNodeConverter() throws IOException {
        Tree<String> tree = new Tree<String>("root");
        tree.addChild("child");

        StringWriter writer = new StringWriter();
        tree.write(new PrintWriter(writer), new Tree.Converter<String>() {
            public String toString(Node<String> node) {
                return "my " + node.getValue();
            }
        });

        BufferedReader reader = new BufferedReader(new StringReader(writer.getBuffer().toString()));

        assertEquals("my root"     , reader.readLine());
        assertEquals("+- my child" , reader.readLine());
    }
