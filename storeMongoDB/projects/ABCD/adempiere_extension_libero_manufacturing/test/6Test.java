	protected void assertCalculateDuration(I_AD_Workflow wf, int qty, double durationExpected)
	{
		I_S_Resource plant = getCreatePlant();
		BigDecimal durationReal = routingService.calculateDuration(wf, plant, BigDecimal.valueOf(qty));
		assertEquals(durationExpected, durationReal.doubleValue());
	}
