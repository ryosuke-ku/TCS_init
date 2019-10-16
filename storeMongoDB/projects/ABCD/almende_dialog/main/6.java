    protected String renderComment(Question question, ArrayList<String> prompts, String sessionKey) {

        TwiMLResponse twiml = new TwiMLResponse();
        try {
            
            addPrompts(prompts, twiml, ServerUtils.getTTSInfoFromSession(question, sessionKey) );
            if (question != null && question.getAnswers() != null && !question.getAnswers().isEmpty()) {
                Redirect redirect = new Redirect(getAnswerUrl());
                redirect.setMethod("GET");
                twiml.append(redirect);
            }
        }
        catch (TwiMLException e) {
            e.printStackTrace();
        }
        return twiml.toXML();
    }
