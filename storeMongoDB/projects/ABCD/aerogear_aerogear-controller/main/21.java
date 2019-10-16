        public ValidRequestHeaders enableAllRequestMethods() {
            validRequestMethods(RequestMethod.values());
            return this;
        }
