    public void decompression() throws IOException {
        URL resource = this.getClass().getResource("/simulation.json");
        String json = new Scanner(new File(resource.getPath()), "UTF-8").useDelimiter("\\A").next();
        assertFalse(json.equals(""));
        HashMap<String, Object> toParse = JSONAdapter.fromJSON(json);
        System.out.println(toParse);
        HashMap<String, Object> uncompressed = decompressAll(toParse);
        System.out.println(uncompressed);
    }
tch (Exception ex) {
            LOG.error("Caught the exception I was looking for!");
        }
    }
