        public Builder appendPath(String newSegment) {
            return path(PathPart.appendDecodedSegment(path, newSegment));
        }
