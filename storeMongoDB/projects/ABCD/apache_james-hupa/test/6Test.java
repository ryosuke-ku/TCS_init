            public void f() {
                List<ImapFolder> folderNodes = arguments(0);
                Assert.assertEquals(4, folderNodes.size());
                Assert.assertEquals(5,cache.getLength());
                cache.storeProxiesCrypt("fld", folderNodes);
                Assert.assertEquals(5,cache.getLength());
                folderNodes = cache.restoreProxiesCrypt(ImapFolder.class, "fld");
                Assert.assertEquals(4, folderNodes.size());
                folderNodes = cache.restoreProxies(ImapFolder.class, "fld");
                Assert.assertEquals(0, folderNodes.size());
            }
