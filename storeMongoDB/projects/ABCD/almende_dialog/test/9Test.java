    private String renderQuestion(Question question, AdapterConfig adapter, Session session) throws Exception {

        TwilioAdapter servlet = new TwilioAdapter();
        Return res = servlet.formQuestion(question, adapter.getConfigId(), remoteAddressVoice, null, session,
                                          new HashMap<String, String>());
        String sessionKey = session != null ? session.getKey() : null;
        if (question != null && !question.getType().equalsIgnoreCase("comment"))
            question = res.question;

        if (question.getType().equalsIgnoreCase("comment")) {
            return servlet.renderComment(question, res.prompts, sessionKey);
        }
        else if (question.getType().equalsIgnoreCase("referral")) {

            String remoteID = remoteAddressVoice;
            String externalCallerId = question.getMediaPropertyValue(MediumType.BROADSOFT,
                                                                     MediaPropertyKey.USE_EXTERNAL_CALLERID);
            Boolean callerId = false;
            if (externalCallerId != null) {
                callerId = Boolean.parseBoolean(externalCallerId);
            }
            if (!callerId) {
                remoteID = adapter.getMyAddress();
            }

            for (int i = 0; i < question.getUrl().size(); i++) {
                String url = question.getUrl().get(i);
                //for(String url : question.getUrl()) {
                if (url.startsWith("tel:")) {
                    // added for release0.4.2 to store the question in the
                    // session,
                    // for triggering an answered event
                    // Check with remoteID we are going to use for the call

                    log.info(String.format("current session key before referral is: %s and remoteId %s", sessionKey,
                                           remoteID));
                    String redirectedId = PhoneNumberUtils.formatNumber(url.replace("tel:", ""), null);
                    if (redirectedId != null) {

                        question.getUrl().set(i, redirectedId);
                    }
                    else {
                        log.severe(String.format("Redirect address is invalid: %s. Ignoring.. ",
                                                 url.replace("tel:", "")));
                    }
                }
            }
            return servlet.renderReferral(question, res.prompts, sessionKey, remoteID);
        }
        else if (question.getType().equalsIgnoreCase("open")) {
            return servlet.renderOpenQuestion(question, res.prompts, sessionKey);
        }
        else if (question.getType().equalsIgnoreCase("closed")) {
            return servlet.renderClosedQuestion(question, res.prompts, sessionKey);
        }

        return null;
    }
