    public void test2() {
        String xml = "<Home><Family><Age>13</Age><Name>bbb</Name></Family><Family><Age>14</Age><Name>ccc</Name></Family><PersonInfo><Age>12</Age><Name>aaa</Name></PersonInfo></Home>";

        Person person = new Person();
        person.setName("aaa");
        person.setAge(12);

        Person f1 = new Person();
        f1.setName("bbb");
        f1.setAge(13);

        Person f2 = new Person();
        f2.setName("ccc");
        f2.setAge(14);

        UserAnno user = new UserAnno();
        user.setPersonInfo(person);
        user.setFriends(Arrays.asList(f1, f2));
        String actXml = RXml.beanToXml(user);
        assertEquals(xml, actXml);

        UserAnno user2 = RXml.xmlToBean(xml, UserAnno.class);
        assertEquals(user, user2);
    }
