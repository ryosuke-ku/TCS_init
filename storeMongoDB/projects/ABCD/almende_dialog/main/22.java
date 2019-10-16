    public boolean isPrivate() {

        Object isPrivate = getProperties().get(Adapter.IS_PRIVATE);
        return isPrivate != null ? Boolean.parseBoolean(isPrivate.toString()) : false;
    }
