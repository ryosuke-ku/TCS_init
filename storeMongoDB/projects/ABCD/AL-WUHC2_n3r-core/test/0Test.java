    public void testBean() {
        Person person = new Person("bingoo", 32, true);
        Eson eson = new Eson().on(person);
        assertEquals("{age:32,man:true,name:bingoo}", eson.toString());
        assertEquals("{age:32,man:true,name:'bingoo'}", eson.valueQuote('\'').toString());
        assertEquals("{age:32,man:true,name:\"bingoo\"}", eson.valueQuote('\"').toString());
        assertEquals("{\"age\":32,\"man\":true,\"name\":\"bingoo\"}", eson.std().toString());

