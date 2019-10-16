    public MediaType fromString(String header) {
        if (header == null) {
            throw new IllegalArgumentException("Media type is null");
        }

        int slashIndex = header.indexOf('/');
        if (slashIndex == -1) {
            throw new IllegalArgumentException("'/' not found");
        }

        String type = header.substring(0, slashIndex);
        String rest = header.substring(slashIndex + 1);

        if (rest == null) {
            rest = "*";
        }

        String subType, paramName = null, paramValue = null;
        int semiIndex = rest.indexOf(';');
        if (semiIndex != -1) {
            subType = rest.substring(0, semiIndex);
            rest = rest.substring(semiIndex + 1);
            int eqIndex = rest.indexOf('=');
            paramName = rest.substring(0, eqIndex).trim();
            paramValue = rest.substring(eqIndex + 1).trim();
        } else {
            subType = rest;

            for (MediaType item : this.getMediaTypes()) {
                if (item.getType().equals(type) && item.getSubtype().equals(subType)) {
                    return item;
                }
            }
        }

        Map<String, String> parameters;

        if (paramName != null) {
            parameters = Collections.singletonMap(paramName, paramValue);
        } else {
            parameters = Collections.emptyMap();
        }

        MediaType mediaType = new MediaType(type, subType, parameters);
        return mediaType;
    }
