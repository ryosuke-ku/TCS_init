    protected String renderReferral(Question question,ArrayList<String> prompts, String sessionKey, String remoteID){
        TwiMLResponse twiml = new TwiMLResponse();

        try {
            addPrompts(prompts, twiml, ServerUtils.getTTSInfoFromSession(question, sessionKey));
            String redirectTimeoutProperty = question
                .getMediaPropertyValue( MediumType.BROADSOFT,
                                        MediaPropertyKey.TIMEOUT );
            String redirectTimeout = redirectTimeoutProperty != null ? redirectTimeoutProperty.replace( "s", "" ) : "30";
            int timeout = 30;
            try {
                timeout = Integer.parseInt( redirectTimeout );
            }
            catch ( NumberFormatException e ) {
                e.printStackTrace();
            }
            
            Dial dial = new Dial();
            
            List<String> urls = question.getUrl();           
            for(String url : urls) {

                if (DDRUtils.validateAddressAndUpdateDDRIfInvalid(url, sessionKey)) {

                    url = url.replace("tel:", "").trim();
                    Number number = new Number( PhoneNumberUtils.formatNumber(url, null));
                    number.setMethod("GET");
                    number.setUrl(getPreconnectUrl());
                    
                    number.setStatusCallback( getCCUrl() );
                    number.setStatusCallbackEvents( "initiated ringing answered completed" );
                    number.setStatusCallbackMethod( "GET" );
                    
                    dial.append(number);
                }
            }
            
            dial.setCallerId( remoteID );
            dial.setTimeout( timeout );

            dial.setMethod( "GET" );
            dial.setAction( getAnswerUrl() );

            twiml.append( dial );
        }
        catch ( TwiMLException e ) {
            log.warning( "Failed to create referal" );
        }
        return twiml.toXML();
    }
