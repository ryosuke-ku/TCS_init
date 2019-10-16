    public void test_for_jh() throws Exception {
        String text = "[{\"I.13\":\"XEMwXFMweGEuMHhjOFxGy87M5VxUxOO6ww==\",\"I.18\":\"MA==\"},{\"I.13\":\"XEMwXFMweGEuMHhjOFxGy87M5VxUxOO6ww==\",\"I.18\":\"MA==\"}]";
        JSON.parse(text);
        JSON.parseArray(text);
    }
