    public void testInputHandler() {
        InputHandler ih = p.getInputHandler();
        assertNotNull(ih);
        assertTrue(ih instanceof DefaultInputHandler);
        InputHandler pfih = new PropertyFileInputHandler();
        p.setInputHandler(pfih);
        assertSame(pfih, p.getInputHandler());
    }
