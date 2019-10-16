	public void testPacemakerScj() {
		try {			
			long t1, t2;
			t1 = System.currentTimeMillis();
			Set<Problem> problems = ScjMemoryScopeAnalysis.buildPointsTo(workingDir+"pacemaker-scj.jar","privmem/pacemakerScj/main", null);
			t2 = System.currentTimeMillis();
			System.out.print("Analysis time: "+(t2-t1)+"\n");
			printProblems(problems);
		} catch (Exception e) {
			System.out.print("Error in unit test\n");
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}
