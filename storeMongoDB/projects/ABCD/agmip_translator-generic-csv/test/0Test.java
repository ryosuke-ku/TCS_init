    public void domeContinueTest() {
        resource = this.getClass().getResource("/continue_test.csv");
        try {
            Map dome = translator.readFile(resource.getPath());
            log.info("Translation results for continue_test: {}", dome.toString());
        } catch (Exception ex) {
            assertTrue(false);
            ex.printStackTrace();
        }
        assertTrue(true);
    }
