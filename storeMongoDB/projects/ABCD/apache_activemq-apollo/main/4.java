    private static void parseComposite(URI uri, CompositeData rc, String ssp) throws URISyntaxException {
        String componentString;
        String params;

        if (!checkParenthesis(ssp)) {
            throw new URISyntaxException(uri.toString(), "Not a matching number of '(' and ')' parenthesis");
        }

        int p;
        int intialParen = ssp.indexOf("(");
        if (intialParen == 0) {
            rc.host = ssp.substring(0, intialParen);
            p = rc.host.indexOf("/");
            if (p >= 0) {
                rc.path = rc.host.substring(p);
                rc.host = rc.host.substring(0, p);
            }
            p = ssp.lastIndexOf(")");
            componentString = ssp.substring(intialParen + 1, p);
            params = ssp.substring(p + 1).trim();

        } else {
            componentString = ssp;
            params = "";
        }

        String components[] = splitComponents(componentString);
        rc.components = new URI[components.length];
        for (int i = 0; i < components.length; i++) {
            rc.components[i] = new URI(components[i].trim());
        }

        p = params.indexOf("?");
        if (p >= 0) {
            if (p > 0) {
                rc.path = stripPrefix(params.substring(0, p), "/");
            }
            rc.parameters = parseQuery(params.substring(p + 1));
        } else {
            if (params.length() > 0) {
                rc.path = stripPrefix(params, "/");
            }
            rc.parameters = emptyMap();
        }
    }
