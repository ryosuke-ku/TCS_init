        public Uri build() {
            if (opaquePart != null) {
                if (this.scheme == null) {
                    throw new UnsupportedOperationException(
                            "An opaque URI must have a scheme.");
                }

                return new OpaqueUri(scheme, opaquePart, fragment);
            } else {
                // Hierarchical URIs should not return null for getPath().
                PathPart path = this.path;
                if (path == null || path == PathPart.NULL) {
                    path = PathPart.EMPTY;
                } else {
                    // If we have a scheme and/or authority, the path must
                    // be absolute. Prepend it with a '/' if necessary.
                    if (hasSchemeOrAuthority()) {
                        path = PathPart.makeAbsolute(path);
                    }
                }

                return new HierarchicalUri(
                        scheme, authority, path, query, fragment);
            }
        }
