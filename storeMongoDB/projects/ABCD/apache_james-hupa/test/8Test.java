    public void testFolderList() {
        storage.gettingFolders().done(new Function() {
            public void f() {
                List<ImapFolder> folderNodes = arguments(0);
                Assert.assertEquals(4, folderNodes.size());
            }
        });
        Assert.assertEquals(5,cache.getLength());

        cache.removeItem("Mock-Sent");
        storage.gettingFolders().done(new Function() {
            public void f() {
                List<ImapFolder> folderNodes = arguments(0);
                Assert.assertEquals(3, folderNodes.size());
            }
        });
        cache.setExpires("fld", 0);
        Assert.assertEquals(0,cache.getLength());

        storage.gettingFolders().done(new Function() {
            public void f() {
                List<ImapFolder> folderNodes = arguments(0);
                Assert.assertEquals(4, folderNodes.size());
            }
        });

        Assert.assertEquals(5,cache.getLength());

        eventBus.fireEvent(new LoginEvent(testUser));
        storage.gettingFolders().done(new Function() {
            public void f() {
                List<ImapFolder> folderNodes = arguments(0);
                Assert.assertEquals(4, folderNodes.size());
            }
        });

        Assert.assertEquals(10,cache.getLength());
    }
