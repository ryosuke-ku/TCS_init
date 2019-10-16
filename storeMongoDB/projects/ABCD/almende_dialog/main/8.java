    protected String renderClosedQuestion(Question question, ArrayList<String> prompts, String sessionKey) {

        try {
            sessionKey = URLEncoder.encode(sessionKey, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        TwiMLResponse twiml = new TwiMLResponse();
        Gather gather = new Gather();
        gather.setAction(getAnswerUrl());
        gather.setMethod("GET");
        gather.setNumDigits(1);

        String noAnswerTimeout = question.getMediaPropertyValue(MediumType.BROADSOFT, MediaPropertyKey.TIMEOUT);
        
        boolean useHash = true;
        if(question.getAnswers().size() > 11) {
        	useHash = false;
        }
        else {
            List<Answer> answers = question.getAnswers();
            for (Answer answer : answers) {
                if (answer != null && answer.getAnswer_text() != null &&
                    answer.getAnswer_text().startsWith("dtmfKey://#")) {

                    useHash = true;
                    break;
                }
            }
        }
        
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
        if(useHash) {
        	gather.setFinishOnKey("");
        }
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

        return twiml.toXML();
    }
