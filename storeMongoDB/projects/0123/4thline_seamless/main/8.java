        public void remove() {
            if (nextIndex == 0)
                throw new IllegalStateException("Call next() first");
            if (removedCurrent)
                throw new IllegalStateException("Already removed current, call next()");
            synchronizedRemove(nextIndex-1);
            removedCurrent = true;
        }
