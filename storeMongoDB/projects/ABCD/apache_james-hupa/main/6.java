    public Promise gettingFolders(final boolean skipCache) {
        return GQuery
        .when(waitingForLogin())
        .then(new Function() {
            public Object f(Object... args) {
                List<ImapFolder> t = skipCache ? null: cache.restoreProxies(ImapFolder.class, KEY_CACHE_FOLDERS);
                if (t != null && !t.isEmpty()) {
                    return GQuery.Deferred().resolve(t).promise();
                } else {
                    return new PromiseRF(rf.fetchFoldersRequest().fetch(null, Boolean.TRUE))
                    .done(new Function(){public void f() {
                        List<ImapFolder> folderNodes = arguments(0);
                        cache.storeProxies(KEY_CACHE_FOLDERS, folderNodes);
                    }});
                }
            }
        });
    }
