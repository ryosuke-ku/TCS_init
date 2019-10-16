        final void writeTo(Parcel parcel) {
            @SuppressWarnings("StringEquality")
            boolean hasEncoded = encoded != NOT_CACHED;

            @SuppressWarnings("StringEquality")
            boolean hasDecoded = decoded != NOT_CACHED;

            if (hasEncoded && hasDecoded) {
                parcel.writeInt(Representation.BOTH);
                parcel.writeString(encoded);
                parcel.writeString(decoded);
            } else if (hasEncoded) {
                parcel.writeInt(Representation.ENCODED);
                parcel.writeString(encoded);
            } else if (hasDecoded) {
                parcel.writeInt(Representation.DECODED);
                parcel.writeString(decoded);
            } else {
                throw new IllegalArgumentException("Neither encoded nor decoded");
            }
        }
