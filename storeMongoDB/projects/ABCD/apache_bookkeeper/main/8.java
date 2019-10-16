        public Cookie build() {
            return new Cookie(layoutVersion, bookieHost, journalDir, ledgerDirs, instanceId);
        }
