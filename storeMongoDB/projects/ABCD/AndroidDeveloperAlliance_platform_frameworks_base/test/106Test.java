    private static void testHierarchical(String scheme, String authority,
            String path, String query, String fragment) {
        StringBuilder sb = new StringBuilder();

        if (authority != null) {
            sb.append("//").append(authority);
        }
        if (path != null) {
            sb.append(path);
        }
        if (query != null) {
            sb.append('?').append(query);
        }

        String ssp = sb.toString();

        if (scheme != null) {
            sb.insert(0, scheme + ":");
        }
        if (fragment != null) {
            sb.append('#').append(fragment);
        }

        String uriString = sb.toString();

        Uri uri = Uri.parse(uriString);

        // Run these twice to test caching.
        compareHierarchical(
                uriString, ssp, uri, scheme, authority, path, query, fragment);
        compareHierarchical(
                uriString, ssp, uri, scheme, authority, path, query, fragment);

        // Test rebuilt version.
        uri = uri.buildUpon().build();

        // Run these twice to test caching.
        compareHierarchical(
                uriString, ssp, uri, scheme, authority, path, query, fragment);
        compareHierarchical(
                uriString, ssp, uri, scheme, authority, path, query, fragment);

        // The decoded and encoded versions of the inputs are all the same.
        // We'll test the actual encoding decoding separately.

        // Test building with encoded versions.
        Uri built = new Uri.Builder()
                .scheme(scheme)
                .encodedAuthority(authority)
                .encodedPath(path)
                .encodedQuery(query)
                .encodedFragment(fragment)
                .build();

        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);
        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);

        // Test building with decoded versions.
        built = new Uri.Builder()
                .scheme(scheme)
                .authority(authority)
                .path(path)
                .query(query)
                .fragment(fragment)
                .build();

        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);
        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);

        // Rebuild.
        built = built.buildUpon().build();

        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);
        compareHierarchical(
                uriString, ssp, built, scheme, authority, path, query, fragment);
    }
