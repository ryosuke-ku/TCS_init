    void invokeCompletedOffsetCommitCallbacks() {
        while (true) {
            OffsetCommitCompletion completion = completedOffsetCommits.poll();
            if (completion == null)
                break;
            completion.invoke();
        }
    }
