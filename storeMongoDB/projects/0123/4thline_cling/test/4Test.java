    protected void assertSampleMixed(DIDLContent didl) throws Exception {

        DescMeta meta0 = didl.getDescMetadata().get(0);
        assertEquals(meta0.getId(), "a1");
        assertEquals(meta0.getType(), "Some Text");
        assertEquals(meta0.getNameSpace().toString(), "urn:my-vendor-extension");
        Document meta0Doc = (Document) meta0.getMetadata();
        assertEquals(documentToString(meta0Doc), "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>" +
                "<desc-wrapper xmlns=\"urn:fourthline-org:cling:support:content-directory-desc-1-0\">" +
                "<vendor:foo xmlns:vendor=\"urn:my-vendor-extension\">" +
                "<vendor:bar vendor:abc=\"123\">aaa</vendor:bar>" +
                "<vendor:baz>bbb</vendor:baz>" +
                "</vendor:foo>" +
                "</desc-wrapper>");

        List<Container> containers = didl.getContainers();
        assertEquals(containers.size(), 1);

        Container slideShowContainer = didl.getContainers().get(0);

        assertEquals(slideShowContainer.getId(), "10");
        assertEquals(slideShowContainer.getParentID(), "5");
        assertEquals(slideShowContainer.getChildCount(), new Integer(3));
        assertEquals(slideShowContainer.isRestricted(), true);
        assertEquals(slideShowContainer.isSearchable(), false);
        assertEquals(slideShowContainer.getTitle(), "Slideshow");
        assertEquals(slideShowContainer.getClazz().getValue(), "object.container.album.photoAlbum");

        DescMeta meta1 = slideShowContainer.getDescMetadata().get(0);
        assertEquals(meta1.getId(), "b1");
        assertEquals(meta1.getType(), "Some Text");
        assertEquals(meta1.getNameSpace().toString(), "urn:my-vendor-extension");
        Document meta1Doc = (Document) meta1.getMetadata();
        assertEquals(documentToString(meta1Doc), "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>" +
                "<desc-wrapper xmlns=\"urn:fourthline-org:cling:support:content-directory-desc-1-0\">" +
                "<vendor:foo xmlns:vendor=\"urn:my-vendor-extension\">bar</vendor:foo>" +
                "</desc-wrapper>");

        List<Item> items = didl.getItems();
        assertEquals(items.size(), 3);

        Item itemOne = items.get(0);
        assertEquals(itemOne.getId(), "6");
        assertEquals(itemOne.getParentID(), "5");
        assert itemOne.isRestricted();
        assertEquals(itemOne.getTitle(), "Chloe Dancer");
        assertEquals(itemOne.getCreator(), "Mother Love Bone");
        assertEquals(itemOne.getClazz().getValue(), "object.item.audioItem.musicTrack");
        Res resource = itemOne.getResources().get(0);
        assertEquals(resource.getProtocolInfo().toString(), "http-get:*:audio/x-ms-wma:*");
        assertEquals(resource.getSize(), new Long(200000));
        assertEquals(resource.getValue(), "http://10.0.0.1/getcontent.asp?id=6");

        DescMeta meta2 = itemOne.getDescMetadata().get(0);
        assertEquals(meta2.getId(), "c1");
        assertEquals(meta2.getType(), "Some Text");
        assertEquals(meta2.getNameSpace().toString(), "urn:my-vendor-extension");
        Document meta2Doc = (Document) meta2.getMetadata();
        assertEquals(documentToString(meta2Doc), "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>" +
                "<desc-wrapper xmlns=\"urn:fourthline-org:cling:support:content-directory-desc-1-0\">" +
                "<vendor:foo xmlns:vendor=\"urn:my-vendor-extension\">bar</vendor:foo>" +
                "</desc-wrapper>");

        DescMeta meta3 = itemOne.getDescMetadata().get(1);
        assertEquals(meta3.getId(), "c2");
        assertEquals(meta3.getType(), "More Text");
        assertEquals(meta3.getNameSpace().toString(), "urn:my-vendor-extension");
        Document meta3Doc = (Document) meta3.getMetadata();
        assertEquals(documentToString(meta3Doc), "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>" +
                "<desc-wrapper xmlns=\"urn:fourthline-org:cling:support:content-directory-desc-1-0\">" +
                "<vendor:foo xmlns:vendor=\"urn:my-vendor-extension\">baz</vendor:foo>" +
                "</desc-wrapper>");

        Item itemTwo = items.get(1);
        assertEquals(itemTwo.getId(), "7");
        assertEquals(itemTwo.getParentID(), "5");
        assert itemTwo.isRestricted();
        assertEquals(itemTwo.getTitle(), "Drown");
        assertEquals(itemTwo.getCreator(), "Smashing Pumpkins");
        assertEquals(itemTwo.getClazz().getValue(), "object.item.audioItem.musicTrack");
        resource = itemTwo.getResources().get(0);
        assertEquals(resource.getProtocolInfo().toString(), "http-get:*:audio/mpeg:*");
        assertEquals(resource.getSize(), new Long(140000));
        assertEquals(resource.getValue(), "http://10.0.0.1/getcontent.asp?id=7");

        Item itemThree = items.get(2);
        assertEquals(itemThree.getId(), "8");
        assertEquals(itemThree.getParentID(), "5");
        assert itemThree.isRestricted();
        assertEquals(itemThree.getTitle(), "State Of Love And Trust");
        assertEquals(itemThree.getCreator(), "Pearl Jam");
        assertEquals(itemThree.getClazz().getValue(), "object.item.audioItem.musicTrack");
        resource = itemThree.getResources().get(0);
        assertEquals(resource.getProtocolInfo().toString(), "http-get:*:audio/x-ms-wma:*");
        assertEquals(resource.getSize(), new Long(70000));
        assertEquals(resource.getValue(), "http://10.0.0.1/getcontent.asp?id=8");

    }
