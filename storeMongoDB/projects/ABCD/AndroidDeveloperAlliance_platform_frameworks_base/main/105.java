        public Builder appendEncodedPath(String newSegment) {
            return path(PathPart.appendEncodedSegment(path, newSegment));
        }
