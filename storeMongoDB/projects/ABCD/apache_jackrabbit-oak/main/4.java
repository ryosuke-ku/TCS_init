    Iterable<String> getPrincipalNames() {
        return Iterables.transform(principals, new Function<Principal, String>() {
            @Override
            public String apply(Principal principal) {
                return principal.getName();
            }
        });
    }
