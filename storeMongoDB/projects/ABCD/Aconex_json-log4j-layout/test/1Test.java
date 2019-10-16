    public void emptyMDCStringShouldResultInEmptyArray(){
        jsonLayout.setMdcKeysToUse("");
        String[] mdckeys = jsonLayout.getMdcKeys();
        assertThat(mdckeys.length, is(0));

        jsonLayout.setMdcKeysToUse(null);
        mdckeys = jsonLayout.getMdcKeys();
        assertThat(mdckeys.length, is(0));
    }
