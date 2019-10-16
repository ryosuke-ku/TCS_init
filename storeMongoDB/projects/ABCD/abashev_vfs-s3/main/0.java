    public void allow (Permission[] permission_list) {
        setRule(permission_list, (byte) 1);
    }
ls(AmazonS3Client.class))) {
            throw new IllegalArgumentException("Not able to extract credentials from " + client.getClass());
        }

        AWSCredentialsProvider provider = null;

        try {
            provider = (AWSCredentialsProvider) awsCredentialsProviderField.get(client);
        } catch (IllegalAccessException e) {
            log.error("Not able to get access to 'awsCredentialsProvider'. Something wrong with your AWS SDK version.", e);
        }

        return (provider != null) ? provider.getCredentials() : null;
    }
