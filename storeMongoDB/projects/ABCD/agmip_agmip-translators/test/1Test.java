	public void test() {		
    AdvancedHashMap data = agMIPFileLoader.readFile("BDJE0XXX.AgMIP");
		aquaCropConverter.writeFile("BDJE0XXX_AquaCrop.tmp", data);
	}
