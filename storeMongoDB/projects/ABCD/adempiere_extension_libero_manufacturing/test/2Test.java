	public void assertEquals(String message, I_PP_MRP expected, I_PP_MRP actual) throws Exception
	{
		boolean equals = expected.getAD_Client_ID() == actual.getAD_Client_ID()
			&& expected.getAD_Org_ID() == actual.getAD_Org_ID()
			&& expected.getM_Warehouse_ID() == actual.getM_Warehouse_ID()
			&& expected.getM_Product_ID() == actual.getM_Product_ID()
			&& expected.getQty().equals(actual.getQty())
			&& expected.getTypeMRP().equals(actual.getTypeMRP())
			&& expected.getDocStatus().equals(actual.getDocStatus())
			&& expected.getDatePromised().equals(actual.getDatePromised())
			&& expected.getDateStartSchedule().equals(actual.getDateStartSchedule())
			&& expected.getDateFinishSchedule().equals(actual.getDateFinishSchedule())
			&& expected.getDateOrdered().equals(actual.getDateOrdered());
		//
		StringBuffer sb = new StringBuffer(message)
							.append(": expected="+expected)
							.append(", actual="+actual);
		
		assertTrue(sb.toString(), equals);
	}
