        public Object get(final Object key) {
            Object o = getReal(key);
            if (o instanceof UnknownElement) {
                // Make sure that
                final UnknownElement ue = (UnknownElement) o;
                ue.maybeConfigure();
                o = ue.getRealThing();
            }
            return o;
        }
