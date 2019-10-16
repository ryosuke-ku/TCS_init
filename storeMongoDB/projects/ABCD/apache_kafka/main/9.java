        public void invoke() {
            if (callback != null)
                callback.onComplete(offsets, exception);
        }
