    protected String renderOpenQuestion(Question question, ArrayList<String> prompts, String sessionKey) {

        TwiMLResponse twiml = new TwiMLResponse();

        String typeProperty = question.getMediaPropertyValue(MediumType.BROADSOFT, MediaPropertyKey.TYPE);
        if (typeProperty != null && typeProperty.equalsIgnoreCase("audio")) {
            renderVoiceMailQuestion(question, prompts, sessionKey, twiml);
        }
        else {

            Gather gather = new Gather();
            gather.setAction(getAnswerUrl());
            gather.setMethod("GET");

            String dtmfMaxLength = question.getMediaPropertyValue(MediumType.BROADSOFT,
                                                                  MediaPropertyKey.ANSWER_INPUT_MAX_LENGTH);
            if (dtmfMaxLength != null) {
                try {
                    int digits = Integer.parseInt(dtmfMaxLength);
                    gather.setNumDigits(digits);
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            String noAnswerTimeout = question.getMediaPropertyValue(MediumType.BROADSOFT, MediaPropertyKey.TIMEOUT);
            //assign a default timeout if one is not specified

            noAnswerTimeout = noAnswerTimeout != null ? noAnswerTimeout : "5";
            if (noAnswerTimeout.endsWith("s")) {
                log.warning("No answer timeout must end with 's'. E.g. 10s. Found: " + noAnswerTimeout);
                noAnswerTimeout = noAnswerTimeout.replace("s", "");
            }
            int timeout = 5;
            try {
                timeout = Integer.parseInt(noAnswerTimeout);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            gather.setTimeout(timeout);

            try {
                addPrompts(prompts, gather, ServerUtils.getTTSInfoFromSession(question, sessionKey));
                twiml.append(gather);
                Redirect redirect = new Redirect(getTimeoutUrl());
                redirect.setMethod("GET");
                twiml.append(redirect);
            }
            catch (TwiMLException e) {
                e.printStackTrace();
            }
        }

        return twiml.toXML();
    }
