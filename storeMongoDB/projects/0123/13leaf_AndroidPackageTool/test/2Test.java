	public void testGetParentDir() throws IOException
	{
		file1=new File("D:\\test\\abc\\..\\abc");file1.mkdirs();
		file2=new File("D:\\test");file2.mkdirs();
		assertEquals("D:\\test", Files.getParentDir(file1.getAbsolutePath(), file2.getAbsolutePath()));
	}
