    public static Competency findByTitle(String title) {
        String query = "select c from Competency c where c.title = ?";
        return Competency.find(query, title).first();
    }
