	protected String doIt() throws Exception
	{
		parameters = new ArrayList<Object>();
		dd_order_id_cache.clear();
		partner_cache.clear(); 
	
		
		StringBuffer whereClause = new StringBuffer(MResource.COLUMNNAME_ManufacturingResourceType+"=? AND AD_Client_ID=?");
		parameters.add(MResource.MANUFACTURINGRESOURCETYPE_Plant);
		parameters.add(getAD_Client_ID());
		if (getPlant_ID() > 0)
		{	
			whereClause.append(" AND "+MResource.COLUMNNAME_S_Resource_ID+"=?");
			parameters.add(getPlant_ID());
		}	
		List <MResource> plants = new Query(getCtx(), MResource.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(parameters)
										.list(); 
		for(MResource plant : plants)
		{	
			log.info("Run MRP to Plant: " + plant.getName());
			this.Planning_Horizon = TimeUtil.addDays(getToday(), plant.getPlanningHorizon()); 
			parameters = new ArrayList<Object>();
			whereClause = new StringBuffer("AD_Client_ID=?");
			parameters.add(getAD_Client_ID());

			if (getAD_Org_ID() > 0)
			{	
				whereClause.append(" AND AD_Org_ID=?");
				parameters.add(getAD_Org_ID());
			}	


			List <MOrg> orgList = new Query(getCtx(),MOrg.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(parameters)
			.list();

			for (MOrg org : orgList)
			{
				// Set Default Document Type To Requisition
				int AD_User_ID =  Env.getAD_User_ID(getCtx());
				docTypeReq_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_PurchaseRequisition,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeMO_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_ManufacturingOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeMF_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_MaintenanceOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeDO_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_DistributionOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				
				log.info("Run MRP to Organization: " + org.getName());
				MWarehouse[] ws;
				if(getM_Warehouse_ID() <= 0)
				{
					ws = MWarehouse.getForOrg(getCtx(), org.getAD_Org_ID());
				}
				else
				{
					ws = new MWarehouse[]{MWarehouse.get(getCtx(), getM_Warehouse_ID())};
				}
				//
				for(MWarehouse w : ws)
				{
					// remove using DRP should be executed
					//if(plant.getM_Warehouse_ID() == w.getM_Warehouse_ID() && isRequiredDRP())
					//	continue;

					log.info("Run MRP to Wharehouse: " + w.getName());
					runMRP(getAD_Client_ID(), org.getAD_Org_ID(), plant.getS_Resource_ID(), w.getM_Warehouse_ID());
					StringBuffer resultMsg = new StringBuffer();
					resultMsg.append("<br> <b> @AD_Org_ID@: </b>" + org.getName());
					resultMsg.append("<b>, @M_Warehouse_ID@: </b>" +w.getName());
					resultMsg.append("<b>, @S_Resource_ID@: </b>" +plant.getName());
					resultMsg.append("<hr>");
					resultMsg.append("<br><b>@PP_Order_ID@:</b> "+count_MO);
					resultMsg.append("<br><b>@DD_Order_ID@:</b> "+count_DO);
					resultMsg.append("<br><b>@M_Requisition_ID@:</b> "+count_MR);
					resultMsg.append("<br><b>@AD_Note_ID@:</b> "+count_Msg);
					count_MO = 0;
					count_MR = 0;
					count_DO = 0;
					count_Msg = 0;
					addLog(resultMsg.toString());
				}
				//resultMsg.append("<br>finish MRP to Organization " +org.getName());
			}
		}		
		//
		return "";
	} 
