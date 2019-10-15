    public void testSetFollowSymlinks() throws IOException, InterruptedException {
        if (supportsSymlinks) {
            File dir = new File(buildRule.getProject().getBaseDir(),
                    "../../../main/org/apache/tools");

            File linkFile = new File(dir, "ThisIsALink");
            assertFalse("link exists pre-test", linkFile.exists());
            File targetFile = new File(dir, "ant");
            assertTrue("target does not exist pre-test", targetFile.exists());

            try {
                // add conditions and more commands as soon as the need arises
                String[] command = new String[] {"ln", "-s", targetFile.getAbsolutePath(), linkFile.getAbsolutePath()};
                Process process = Runtime.getRuntime().exec(command);
                assertEquals("0 return code expected for external process", 0, process.waitFor());

                // followSymlinks should be true by default, but if this ever
                // changes we will need this line.
                ds.setFollowSymlinks(true);

                ds.setBasedir(dir);
                ds.setExcludes(new String[] {"ant/**"});
                ds.scan();

                boolean haveZipPackage = false;
                boolean haveTaskdefsPackage = false;

                String[] includeds = ds.getIncludedDirectories();
                for (String included : includeds) {
                    if (included.equals("zip")) {
                        haveZipPackage = true;
                    } else if (included.equals("ThisIsALink" + File.separator + "taskdefs")) {
                        haveTaskdefsPackage = true;
                    }
                }

                // if we followed the symlink we just made we should
                // bypass the excludes.

                assertTrue("(1) zip package included", haveZipPackage);
                assertTrue("(1) taskdefs package included", haveTaskdefsPackage);

                ds = new DirectoryScanner();
                ds.setFollowSymlinks(false);

                ds.setBasedir(dir);
                ds.setExcludes(new String[] {"ant/**"});
                ds.scan();

                haveZipPackage = false;
                haveTaskdefsPackage = false;
                includeds = ds.getIncludedDirectories();
                for (String included : includeds) {
                    if (included.equals("zip")) {
                        haveZipPackage = true;
                    } else if (included.equals("ThisIsALink" + File.separator + "taskdefs")) {
                        haveTaskdefsPackage = true;
                    }
                }
                assertTrue("(2) zip package included", haveZipPackage);
                assertFalse("(2) taskdefs package not included", haveTaskdefsPackage);

            } finally {
                if (!linkFile.delete()) {
                    //TODO log this?
                    //throw new RuntimeException("Failed to delete " + linkFile);
                }

            }
        }
    }
