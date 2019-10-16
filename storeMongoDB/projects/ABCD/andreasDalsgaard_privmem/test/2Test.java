	public void testmd5scj() {
		try {			
			long t1, t2;
			t1 = System.currentTimeMillis();
			Set<Problem> problems = ScjMemoryScopeAnalysis.buildPointsTo(workingDir+"md5scj.jar","privmem/md5scj/Level0App", null);
			t2 = System.currentTimeMillis();
			System.out.print("Analysis time: "+(t2-t1)+"\n");
			printProblems(problems);
		} catch (Exception e) {
			System.out.print("Error in unit test\n");
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}
