    private void grabReviews(List<JSONObject> models) {
        log.info("Grabbing reviews to DB started");
        for(JSONObject model : models){
            try {
                //Here can be thrown JSONException - if there are no such keys in json object "model"
                long modelId = model.getLong(JSONKeys.ID.getKey());

                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put(RequestParams.COUNT.getKey(), Integer.toString(MAX_REVIEWS_NUM_PER_PAGE));
                parameters.put(RequestParams.PAGE.getKey(), "1");

                OpinionRequestBuilder opinionRequestBuilder = new OpinionRequestBuilder();
                UrlRequest urlRequest = opinionRequestBuilder.requestForOpinionOnModelById(modelId, parameters);

                List<JSONObject> reviewsList = null;
                try {
                    //Here can be thrown HttpException or IOException - if something wrong in json object downloading
                    JSONObject modelOpinions = contentApiProvider.provide(urlRequest);

                    //Here can be thrown JSONException - if something wrong with received json object
                    reviewsList = JSONUtil.extractList(modelOpinions, JSONKeys.OPINION.getKey(), JSONKeys.MODEL_OPINIONS.getKey());

                    //If everything Ok - processing valid json object
                    setModelId(reviewsList, modelId);
                    processEntityList(reviewsList);

                    try{
                        //Here can be thrown JSONException - if there are no such keys in json object "modelOpinions"
                        JSONObject opinionsInnerData = modelOpinions.getJSONObject(JSONKeys.MODEL_OPINIONS.getKey());
                        try{
                            //Here can be thrown JSONException - if there are no such keys in json object "total"
                            int opinionsCount = opinionsInnerData.getInt(JSONKeys.TOTAL.getKey());

                            if(opinionsCount > MAX_REVIEWS_NUM_PER_PAGE){
                                int pageCount = (opinionsCount % MAX_REVIEWS_NUM_PER_PAGE == 0) ? (opinionsCount / MAX_REVIEWS_NUM_PER_PAGE) : (opinionsCount / MAX_REVIEWS_NUM_PER_PAGE) + 1;
                                for(int pageNumber = 2; pageNumber <= pageCount; pageNumber++){

                                    parameters = new HashMap<String, String>();
                                    parameters.put(RequestParams.PAGE.getKey(), Integer.toString(pageNumber));

                                    urlRequest = opinionRequestBuilder.requestForOpinionOnModelById(modelId, parameters);
                                    try{
                                        //Here can be thrown HttpException or IOException - if something wrong in json object downloading
                                        modelOpinions = contentApiProvider.provide(urlRequest);

                                        //Here can be thrown JSONException - if something wrong with received json object
                                        reviewsList = JSONUtil.extractList(modelOpinions, JSONKeys.OPINION.getKey(), JSONKeys.MODEL_OPINIONS.getKey());

                                        //If everything Ok - processing valid json object
                                        setModelId(reviewsList, modelId);
                                        processEntityList(reviewsList);
                                    }catch (JSONException e) {
                                        log.error("Error while parsing json object, received " +
                                                "by url: " + urlRequest.getUrl(), e);
                                    }
                                }
                            }
                            update(model, opinionsCount);
                        } catch (JSONException e) {
                            log.error("Error while parsing json object " + opinionsInnerData + " : no such key \"total\"", e);
                            update(model, 0);
                        }
                    } catch (JSONException e) {
                        log.error("Error while parsing json object " + modelOpinions + " : no such key \"modelOpinions\"", e);
                        update(model, 0);
                    }
                }catch (HTTPException e){
                    log.error("Http error. " + e.getMessage());
                    update(model, 0);
                } catch (IOException e) {
                    log.error("Error in JSON object transfer. " + "\n" +
                            "Request url: " + urlRequest.getUrl());
                    update(model, 0);
                }catch (JSONException e) {
                    log.error("Error while parsing json object, received " +
                            "by url: " + urlRequest.getUrl(), e);
                    update(model, 0);
                }
            } catch (JSONException e) {
                log.error("Error while parsing json object " + model + " : no such key \"model\"", e);
            }
        }
        log.info("Grabbing reviews to DB ended");
    }
