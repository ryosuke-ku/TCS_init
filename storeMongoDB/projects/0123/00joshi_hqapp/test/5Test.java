  public void testAttachmentObj() throws Exception {
    AttachmentService service = CouchServiceFactory.get(AttachmentService.class, SOMEDB);

    DataWithAttachments data = new DataWithAttachments();
    CouchTx tx = service.persist(data);
    assert tx.ok() : tx.error();

    data = service.find(tx.id());
    assert data._attachments.size() == 0;

    String content = "a bunch \nof data in a file";
    tx = service.attachments(data).addAttachment(content, "file.txt", "text/plain");
    assert tx.ok() : tx.error();

    data = service.find(tx.id());

    assert data._attachments.size() == 1;
    assert content.length() == data._attachments.get("file.txt").getLength();
    assert "text/plain".equals(data._attachments.get("file.txt").getContentType());

    // make sure changing other properties doesnt break the attachments map
    data.data = "asdf";
    tx = service.persist(data);
    assert tx.ok() : tx.error();

    data = service.find(tx.id());

    assert data._attachments.size() == 1;
    assert content.length() == data._attachments.get("file.txt").getLength();
    assert "text/plain".equals(data._attachments.get("file.txt").getContentType());

    // update an attachment, make sure it takes the new length and same content type
    String content2 = "new, shorter file";
    tx = service.attachments(data).updateAttachment(content2, "file.txt", "text/plain");
    assert tx.ok() : tx.error();

    data = service.find(tx.id());
    assert data._attachments.size() == 1;
    assert content2.length() == data._attachments.get("file.txt").getLength();
    assert "text/plain".equals(data._attachments.get("file.txt").getContentType());

    // modify contenttype, make sure it takes
    tx = service.attachments(data).updateAttachment("<html></html>", "file.txt", "text/html");
    assert tx.ok() : tx.error();
    data = service.find(tx.id());
    assert "text/html".equals(data._attachments.get("file.txt").getContentType());

    // remove an item from the attachments map, be sure it persists
    data._attachments.remove("file.txt");
    tx = service.persist(data);
    assert tx.ok() : tx.error();
    data = service.find(tx.id());
    assert data._attachments.size() == 0;
  }
