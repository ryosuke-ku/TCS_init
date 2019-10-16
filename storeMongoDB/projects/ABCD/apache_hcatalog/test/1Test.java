    protected void runTest(int count) throws Exception {
        nodes = new WriteLock[count];
        for (int i = 0; i < count; i++) {
            ZooKeeper keeper = createClient();
            WriteLock leader = new WriteLock(keeper, dir, null);
            leader.setLockListener(new LockCallback());
            nodes[i] = leader;

            leader.lock();
        }

        // lets wait for any previous leaders to die and one of our new
        // nodes to become the new leader
        latch.await(30, TimeUnit.SECONDS);

        WriteLock first = nodes[0];
        dumpNodes(count);

        // lets assert that the first election is the leader
        Assert.assertTrue("The first znode should be the leader " + first.getId(), first.isOwner());

        for (int i = 1; i < count; i++) {
            WriteLock node = nodes[i];
            Assert.assertFalse("Node should not be the leader " + node.getId(), node.isOwner());
        }

        if (count > 1) {
            if (killLeader) {
                System.out.println("Now killing the leader");
                // now lets kill the leader
                latch = new CountDownLatch(1);
                first.unlock();
                latch.await(30, TimeUnit.SECONDS);
                //Thread.sleep(10000);
                WriteLock second = nodes[1];
                dumpNodes(count);
                // lets assert that the first election is the leader
                Assert.assertTrue("The second znode should be the leader " + second.getId(), second.isOwner());

                for (int i = 2; i < count; i++) {
                    WriteLock node = nodes[i];
                    Assert.assertFalse("Node should not be the leader " + node.getId(), node.isOwner());
                }
            }


            if (restartServer) {
                // now lets stop the server
                System.out.println("Now stopping the server");
                stopServer();
                Thread.sleep(10000);

                // TODO lets assert that we are no longer the leader
                dumpNodes(count);

                System.out.println("Starting the server");
                startServer();
                Thread.sleep(10000);

                for (int i = 0; i < count - 1; i++) {
                    System.out.println("Calling acquire for node: " + i);
                    nodes[i].lock();
                }
                dumpNodes(count);
                System.out.println("Now closing down...");
            }
        }
    }
