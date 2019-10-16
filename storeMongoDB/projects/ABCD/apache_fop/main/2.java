    protected void writeTriplets(OutputStream os) throws IOException {
        if (hasTriplets()) {
            writeObjects(triplets, os);
            triplets = null; // gc
        }
    }
