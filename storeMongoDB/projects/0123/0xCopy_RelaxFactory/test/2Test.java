  public void testAttachments() throws Exception {
    SlightlyComplexCouchService service =
        CouchServiceFactory.get(SlightlyComplexCouchService.class, SOMEDB);

    CSFTest data1 = new CSFTest();
    data1.brand = "asdf";

    CouchTx tx = service.persist(data1);
    assert tx.ok();

    data1 = service.find(tx.id());

    tx =
        service.attachments(data1).addAttachment("package foo.bar.baz;", "foo/bar/baz.java",
            "text/java");
    assert tx.ok();

    String content = service.attachments(data1).getAttachment("foo/bar/baz.java");
    assert content.equals("package foo.bar.baz;");
  }
