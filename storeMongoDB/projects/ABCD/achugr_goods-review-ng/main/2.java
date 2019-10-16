    public void grabAllReviews(){
        ModelGrabber modelGrabber = setUpModelGrabber();
        List<JSONObject> allModels = modelGrabber.grabAllModels();
        grabReviews(allModels);
    }
