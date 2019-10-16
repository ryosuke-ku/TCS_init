    public static MediaPlayerProxy createVariableSpeed(Executor executor)
            throws UnsupportedOperationException {
        return new SingleThreadedMediaPlayerProxy(new VariableSpeed(executor));
    }
