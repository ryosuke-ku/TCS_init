	public void test_simple_one_level_compare_names_only()
	{
		FactorGraph[] fgs1
			= Helpers.MakeSimpleThreeLevelGraphs();
		FactorGraph[] fgs2
			= Helpers.MakeSimpleThreeLevelGraphs();
		
		FactorGraph fgLeaf1 = fgs1[fgs1.length - 1];
		FactorGraph fgLeaf2 = fgs2[fgs2.length - 1];
		
		compareToSelf(fgLeaf1);
		compareToSelf(fgLeaf2);
		
		Discrete vB12 = requireNonNull((Discrete) fgLeaf2.getObjectByName("vLeafB1"));
		Discrete vO12 = requireNonNull((Discrete) fgLeaf2.getObjectByName("vLeafO1"));
		Discrete vO22 = requireNonNull((Discrete) fgLeaf2.getObjectByName("vLeafO2"));
		Factor fLeaf2 = requireNonNull((Factor) fgLeaf2.getObjectByName("fLeaf"));
		
		//Name change, change back, boundary
		vB12.setName("x");
		FactorGraphDiffs diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		assertTrue(!diffs.noDiffs());
		vB12.setName("vLeafB1");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		diffs.toString();
		assertTrue(diffs.noDiffs());
		
		
		//Name change, change back, owned
		vO12.setName("x");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		diffs.toString();
		assertTrue(!diffs.noDiffs());
		vO12.setName("vLeafO1");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		assertTrue(diffs.noDiffs());

		//Name change, change back, factor
		fLeaf2.setName("x");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		diffs.toString();
		assertTrue(!diffs.noDiffs());
		fLeaf2.setName("fLeaf");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		assertTrue(diffs.noDiffs());
		
		//Name change, change back, multiple
		vB12.setName("1");
		vO12.setName("2");
		vO22.setName("3");
		fLeaf2.setName("4");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		diffs.toString();
		assertTrue(!diffs.noDiffs());
		vB12.setName("vLeafB1");
		vO12.setName("vLeafO1");
		vO22.setName("vLeafO2");
		fLeaf2.setName("fLeaf");
		diffs = fgLeaf1.getFactorGraphDiffsByName(fgLeaf2);
		diffs.toString();
		assertTrue(diffs.noDiffs());
	}
