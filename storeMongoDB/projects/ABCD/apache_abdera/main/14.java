    public static boolean matches(EntityTag etag, String... material) {
        EntityTag etag2 = generate(material);
        return EntityTag.matches(etag, etag2);
    }
