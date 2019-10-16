        public void expect(String subString, String subString2) throws IllegalArgumentException{
            String line = getLine();
            if(!line.toUpperCase().contains(subString.toUpperCase()))
                throw new IllegalArgumentException("Expected \"" + subString + "\" in: \"" + line + "\"");
            if(!line.toUpperCase().contains(subString2.toUpperCase()))
                throw new IllegalArgumentException("Expected \"" + subString + "\" in: \"" + line + "\"");
        }
