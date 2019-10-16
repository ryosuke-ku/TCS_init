	public void compositeSequenceStartwithFail_tct038() {
		
		CompositeType compo = (CompositeType) waitForComponentByName(null,
				"CompositeSequenceStartwithFail_tct038");
		
		compo.createInstance(null, null);
		apam.waitForIt(1*1000);

		Implementation implOK = CST.componentBroker.getImpl("ImplemOK");
		Assert.assertNotNull("ImplemOK should have been installed" , implOK );
		Assert.assertNotNull("An Instance of ImplemOK should have been created" , implOK.getInst() );
		Assert.assertTrue("The instance name should be thisOneOK (from the composite)" , implOK.getInst().getName().equals("thisOneOK") );
		
		Implementation implFailing = CST.componentBroker.getImpl("ImplFailing");
		Assert.assertNull("ImplemFailing should NOT have been installed" , implFailing );
		System.out.println("Test finished correctly");
	}	
