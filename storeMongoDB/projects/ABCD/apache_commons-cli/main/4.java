        public Builder hasArg(final boolean hasArg)
        {
            // set to UNINITIALIZED when no arg is specified to be compatible with OptionBuilder
            numberOfArgs = hasArg ? 1 : Option.UNINITIALIZED;
            return this;
        }
