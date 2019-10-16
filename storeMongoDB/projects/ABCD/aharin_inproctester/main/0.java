    public static String getRequestPath(URI uri) {
        String path = uri.getRawPath();
        String query = uri.getRawQuery();
        String fragment = uri.getRawFragment();

        StringBuilder sb = new StringBuilder(path);
        if (query != null && query.length() > 0) {
            sb.append("?").append(query);
        }
        if (fragment != null && fragment.length() > 0) {
            sb.append("#").append(fragment);
        }
        return sb.toString();
    }
ePart.trim().toLowerCase().startsWith("expires=")) {
                String expiresDateString = cookiePart.split("=")[1];
                try {
                    expiresDate = dateFormat.parse(expiresDateString);
                } catch (ParseException ignored) {
                }
            }
        }

        return new Cookie(hostName, cookieName, cookieValue, "/", expiresDate, false);
    }
