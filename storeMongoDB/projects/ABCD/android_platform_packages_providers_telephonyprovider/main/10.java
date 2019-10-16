        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof MmsBody)) {
                return false;
            }
            MmsBody typedObj = (MmsBody) obj;
            return this.text.equals(typedObj.text) && this.charSet == typedObj.charSet;
        }
