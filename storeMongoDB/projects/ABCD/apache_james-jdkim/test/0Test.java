    public boolean tagValuesEquals(String t1, String t2) {
        TagValue tv1 = new TagValue(t1);
        TagValue tv2 = new TagValue(t2);
        boolean eq = tv1.equals(tv2);
        if (eq)
            assertTrue(tv1.hashCode() == tv2.hashCode());
        return eq;
    }
