        public Builder encodedAuthority(String authority) {
            return authority(Part.fromEncoded(authority));
        }
