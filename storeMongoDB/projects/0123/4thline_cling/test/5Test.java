    public void readWriteItemsMinimal() throws Exception {

        DIDLContent didl = parser.parseResource("org/fourthline/cling/test/support/contentdirectory/samples/browseItemsMinimal.xml");
        assertEquals(didl.getContainers().size(), 0);

        List<Item> items = didl.getItems();
        assertEquals(items.size(), 1);
        Item itemOne = items.get(0);
        assertEquals(itemOne.getId(), "1");
        assertEquals(itemOne.getParentID(), "0");
        assertFalse(!itemOne.isRestricted());
        assertEquals(itemOne.getTitle(), "Chloe Dancer");
        assertNull(itemOne.getCreator());
        assertEquals(itemOne.getClazz().getValue(), "object.item.audioItem.musicTrack");
        Res resource = itemOne.getResources().get(0);
        assertEquals(resource.getProtocolInfo().toString(), "http-get:*:audio/x-ms-wma:*");
        assertNull(resource.getSize());
        assertEquals(resource.getValue(), "http://10.0.0.1/somecontent.wma");
        assertNull(resource.getDuration());
        assertNull(resource.getBitrate());
        assertNull(resource.getSampleFrequency());
        assertNull(resource.getBitsPerSample());
        assertNull(resource.getNrAudioChannels());
        assertEquals(resource.getResolutionX(), 0);
        assertEquals(resource.getResolutionY(), 0);
        assertNull(resource.getColorDepth());
        assertNull(resource.getProtection());
        assertNull(resource.getImportUri());

        String xml = parser.generate(didl);

        parser.debugXML(xml);

        didl = parser.parse(xml);

        items = didl.getItems();
        assertEquals(items.size(), 1);
        itemOne = items.get(0);
        assertEquals(itemOne.getId(), "1");
        assertEquals(itemOne.getParentID(), "0");
        assertFalse(!itemOne.isRestricted());
        assertEquals(itemOne.getTitle(), "Chloe Dancer");
        assertNull(itemOne.getCreator());
        assertEquals(itemOne.getClazz().getValue(), "object.item.audioItem.musicTrack");
        resource = itemOne.getResources().get(0);
        assertEquals(resource.getProtocolInfo().toString(), "http-get:*:audio/x-ms-wma:*");
        assertNull(resource.getSize());
        assertEquals(resource.getValue(), "http://10.0.0.1/somecontent.wma");
        assertNull(resource.getDuration());
        assertNull(resource.getBitrate());
        assertNull(resource.getSampleFrequency());
        assertNull(resource.getBitsPerSample());
        assertNull(resource.getNrAudioChannels());
        assertEquals(resource.getResolutionX(), 0);
        assertEquals(resource.getResolutionY(), 0);
        assertNull(resource.getColorDepth());
        assertNull(resource.getProtection());
        assertNull(resource.getImportUri());
    }
