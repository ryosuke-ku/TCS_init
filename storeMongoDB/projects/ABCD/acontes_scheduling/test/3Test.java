    public void run() throws Throwable {
        String scriptPath;
        String propertyPath = new File(getClass().getResource("scripts/SampleCheckProperties").toURI())
                .getAbsolutePath();

        log("Test javaScript evaluation");
        scriptPath = new File(getClass().getResource("scripts/checkProperties.js").toURI()).getAbsolutePath();
        Assert.assertEquals("selected=true", EngineScript.EvalScript(scriptPath,
                EngineScript.Language.javascript, propertyPath));

        log("Test python evaluation");
        scriptPath = new File(getClass().getResource("scripts/checkProperties.py").toURI()).getAbsolutePath();
        Assert.assertEquals("selected=1", EngineScript.EvalScript(scriptPath, EngineScript.Language.python,
                propertyPath));

        log("Test ruby evaluation");
        scriptPath = new File(getClass().getResource("scripts/checkProperties.rb").toURI()).getAbsolutePath();
        Assert.assertEquals("selected=true", EngineScript.EvalScript(scriptPath, EngineScript.Language.ruby,
                propertyPath));

        log("checkOSName");
        Assert.assertFalse(SelectionUtils.checkOSName(null));
        Assert.assertFalse(SelectionUtils.checkOSName("123"));
        Assert.assertTrue(SelectionUtils.checkOSName(System.getProperty("os.name")));
        Assert.assertTrue(SelectionUtils.checkOSName(System.getProperty("os.name").toUpperCase()));

        log("checkOSArch");
        Assert.assertFalse(SelectionUtils.checkOSArch(null));
        Assert.assertTrue(SelectionUtils.checkOSArch(System.getProperty("os.arch")));
        Assert.assertTrue(SelectionUtils.checkOSArch(System.getProperty("os.arch").toUpperCase()));
        Assert.assertTrue(SelectionUtils.checkOSArch("6"));

        log("checkOSVersion");
        Assert.assertFalse(SelectionUtils.checkOSVersion(null));
        Assert.assertFalse(SelectionUtils.checkOSVersion("1.6"));
        Assert.assertTrue(SelectionUtils.checkOSVersion(System.getProperty("os.version")));

        log("checkJavaProperty");
        Assert.assertFalse(SelectionUtils.checkJavaProperty(null, null));
        Assert.assertFalse(SelectionUtils.checkJavaProperty("foo", null));
        Assert.assertFalse(SelectionUtils.checkJavaProperty(null, "bar"));
        Assert.assertFalse(SelectionUtils.checkJavaProperty("foo", "bar"));
        Assert.assertFalse(SelectionUtils.checkJavaProperty("java.home", "bar"));
        Assert.assertTrue(SelectionUtils.checkJavaProperty("java.vm.version", System
                .getProperty("java.vm.version")));
        Assert.assertTrue(SelectionUtils.checkJavaProperty("java.home", ".*"));

        log("checkFreeMemory");
        Assert.assertFalse(SelectionUtils.checkFreeMemory(Runtime.getRuntime().freeMemory() + 1));
        Assert.assertTrue(SelectionUtils.checkFreeMemory(Runtime.getRuntime().freeMemory()));
        Assert.assertTrue(SelectionUtils.checkFreeMemory(Runtime.getRuntime().freeMemory() - 1));

        log("checkExec");
        Assert.assertFalse(SelectionUtils.checkExec(null));
        Assert.assertFalse(SelectionUtils.checkExec(""));
        String path = System.getenv("PATH");
        String[] tokens = path.split(File.pathSeparator);
        //Find first PATH entry that contains an executable
        //If no exec is found, test is considered as passed ! (this case should never happen)
        for (String s : tokens) {
            File dir = new File(s);
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                Assert.assertTrue(SelectionUtils.checkExec(files[0].getName()));
                break;
            }
        }
    }
