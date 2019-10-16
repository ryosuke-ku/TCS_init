        public Option build()
        {
            if (opt == null && longOpt == null)
            {
                throw new IllegalArgumentException("Either opt or longOpt must be specified");
            }
            return new Option(this);
        }
