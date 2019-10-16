	public BigDecimal calculateDuration(I_AD_Workflow wf, I_S_Resource plant, BigDecimal qty)
	{
		if (plant == null)
			return Env.ZERO;
		final Properties ctx = ((PO)wf).getCtx();
		final MResourceType S_ResourceType = MResourceType.get(ctx, plant.getS_ResourceType_ID());  	

		BigDecimal AvailableDayTime  = BigDecimal.valueOf(S_ResourceType.getTimeSlotHours());
		int AvailableDays = S_ResourceType.getAvailableDaysWeek();

		double durationBaseSec = getDurationBaseSec(wf.getDurationUnit());

		double durationTotal = 0.0; 
		MWFNode[] nodes = ((MWorkflow)wf).getNodes(false, Env.getAD_Client_ID(ctx));
		for (I_AD_WF_Node node : nodes)
		{
			// Qty independent times:
			durationTotal += node.getQueuingTime();
			durationTotal += node.getSetupTime();
			durationTotal += node.getWaitingTime();
			durationTotal += node.getMovingTime();
			
			// Get OverlapUnits - number of units that must be completed before they are moved the next activity 
			double overlapUnits = qty.doubleValue();
			if (node.getOverlapUnits() > 0 && node.getOverlapUnits() < overlapUnits)
			{
				overlapUnits = node.getOverlapUnits();
			}
			double durationBeforeOverlap = node.getDuration() * overlapUnits;
			
			durationTotal += durationBeforeOverlap;
		}
		BigDecimal requiredTime = BigDecimal.valueOf(durationTotal * durationBaseSec / 60 / 60);
		// TODO: implement here, Victor's suggestion - https://sourceforge.net/forum/message.php?msg_id=5179460

		// Weekly Factor  	
		BigDecimal WeeklyFactor = BigDecimal.valueOf(7).divide(BigDecimal.valueOf(AvailableDays), 8, RoundingMode.UP);

		return (requiredTime.multiply(WeeklyFactor)).divide(AvailableDayTime, 0, RoundingMode.UP);
	}
