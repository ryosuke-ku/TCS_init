    public void testResolveFile() {
        if (Os.isFamily("netware") || Os.isFamily("dos")) {
            assertEqualsIgnoreDriveCase(localize(File.separator),
                p.resolveFile("/", null).getPath());
            assertEqualsIgnoreDriveCase(localize(File.separator),
                p.resolveFile("\\", null).getPath());
            /*
             * throw in drive letters
             */
            String driveSpec = "C:";
            String driveSpecLower = "c:";
            
            assertEqualsIgnoreDriveCase(driveSpecLower + "\\",
                         p.resolveFile(driveSpec + "/", null).getPath());
            assertEqualsIgnoreDriveCase(driveSpecLower + "\\",
                         p.resolveFile(driveSpec + "\\", null).getPath());
            assertEqualsIgnoreDriveCase(driveSpecLower + "\\",
                         p.resolveFile(driveSpecLower + "/", null).getPath());
            assertEqualsIgnoreDriveCase(driveSpecLower + "\\",
                         p.resolveFile(driveSpecLower + "\\", null).getPath());
            /*
             * promised to eliminate consecutive slashes after drive letter.
             */
            assertEqualsIgnoreDriveCase(driveSpec + "\\",
                         p.resolveFile(driveSpec + "/////", null).getPath());
            assertEqualsIgnoreDriveCase(driveSpec + "\\",
                         p.resolveFile(driveSpec + "\\\\\\\\\\\\", null).getPath());
        } else {
            /*
             * Start with simple absolute file names.
             */
            assertEquals(File.separator,
                         p.resolveFile("/", null).getPath());
            assertEquals(File.separator,
                         p.resolveFile("\\", null).getPath());
            /*
             * drive letters are not used, just to be considered as normal
             * part of a name
             */
            String driveSpec = "C:";
            String udir = System.getProperty("user.dir") + File.separatorChar;
            assertEquals(udir + driveSpec,
                         p.resolveFile(driveSpec + "/", null).getPath());
            assertEquals(udir + driveSpec,
                         p.resolveFile(driveSpec + "\\", null).getPath());
            String driveSpecLower = "c:";
            assertEquals(udir + driveSpecLower,
                         p.resolveFile(driveSpecLower + "/", null).getPath());
            assertEquals(udir + driveSpecLower,
                         p.resolveFile(driveSpecLower + "\\", null).getPath());
        }
        /*
         * Now test some relative file name magic.
         */
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("./4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile(".\\4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("./.\\4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("../3/4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("..\\3\\4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("../../5/.././2/./3/6/../4", new File(localize("/1/2/3"))).getPath());
        assertEquals(localize("/1/2/3/4"),
                     p.resolveFile("..\\../5/..\\./2/./3/6\\../4", new File(localize("/1/2/3"))).getPath());

    }
