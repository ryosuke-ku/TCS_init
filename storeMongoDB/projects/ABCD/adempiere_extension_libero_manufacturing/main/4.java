			 public void run(String trxName)
			 {
				
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					MProduct product = null;                                                                       
					int BeforePP_MRP_ID = 0;						
					Timestamp  BeforeDateStartSchedule = null;
					Timestamp  POQDateStartSchedule = null;
					
					int lowlevel = MPPMRP.getMaxLowLevel(getCtx(), trxName);
					log.info("Low Level Is :"+lowlevel);
					// Calculate MRP for all levels
					for (int level = 0 ; level <= lowlevel ; level++)
					{
						log.info("Current Level Is :" + level);
						StringBuilder sql = new StringBuilder();
						sql.append(
								"SELECT mrp.M_Product_ID, mrp.LowLevel, mrp.Qty, mrp.DatePromised")
								.append(",mrp.TypeMRP, mrp.OrderType, mrp.DateOrdered, mrp.M_Warehouse_ID")
								.append(",mrp.PP_MRP_ID, mrp.DateStartSchedule, mrp.DateFinishSchedule")
								.append(" FROM RV_PP_MRP mrp WHERE 1=1 ")
								.append(getSQLWhere("mrp",AD_Client_ID, AD_Org_ID, M_Warehouse_ID, null, null , level, MPPMRP.TYPEMRP_Demand, Planning_Horizon))
								.append(" ORDER BY  mrp.M_Product_ID , mrp.DatePromised");
									
						pstmt = DB.prepareStatement (sql.toString(), trxName);
						DB.setParameters(pstmt, parameters);
						rs = pstmt.executeQuery();
						log.info("Records "+ rs.getFetchSize()+ " to process for Low Code:" + level);
						while (rs.next())
						{
							final int PP_MRP_ID = rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID);
							final String TypeMRP = rs.getString(MPPMRP.COLUMNNAME_TypeMRP);
							final String OrderType = rs.getString(MPPMRP.COLUMNNAME_OrderType);
							final Timestamp DatePromised = rs.getTimestamp(MPPMRP.COLUMNNAME_DatePromised);
							final BigDecimal Qty = rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty);
							final int M_Product_ID = rs.getInt(MPPMRP.COLUMNNAME_M_Product_ID); 
		
							// if demand is forecast and promised date less than or equal to today, ignore this QtyGrossReq
							if (MPPMRP.TYPEMRP_Demand.equals(TypeMRP)
									&& MPPMRP.ORDERTYPE_Forecast.equals(OrderType)
									&& DatePromised.compareTo(getToday()) <= 0)
							{
								continue;  
							}
		
							// New Product
							if (product == null || product.get_ID() != M_Product_ID)
							{
								// If exist QtyGrossReqs of last Demand verify/calculate plan
								if (QtyGrossReqs.signum() != 0)
								{
									if (product == null)
									{
										throw new IllegalStateException("MRP Internal Error: QtyGrossReqs="+QtyGrossReqs
																		+" and we do not have previous demand defined");
									}
									if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy())
											&& POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
									{
										BeforeDateStartSchedule =  POQDateStartSchedule; 
										calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule, trxName);
									}
									else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy())
											&& BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0)
									{
										// TODO: Q: when we have this situation because on LFL we balance the Demand imediately
										//		so we do not cumullate it?
										calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule , trxName);
									}
									// Discard QtyGrossReqs because:
									// * was already balanced by calculatePlan
									// * is out of Planning Horizon
									QtyGrossReqs = Env.ZERO;
								}
								
								//Setting MRP Change net Update out the model validator and out transaction
								if(m_product_planning != null)
									MPPMRP.setIsRequired(m_product_planning, MPPProductPlanning.COLUMNNAME_IsRequiredMRP , false, trxName);
		
								// Load Product & define Product Data Planning
								product = MProduct.get(getCtx(), M_Product_ID);
								log.info("Calculte Plan to this Product:" + product);
								setProduct(AD_Client_ID,AD_Org_ID ,S_Resource_ID , M_Warehouse_ID,  product, PP_MRP_ID, trxName);
								
								// If No Product Planning found, go to next MRP record 
								if (m_product_planning == null)
									continue;	  
									
								if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
								{
									POQDateStartSchedule =null;
								}
							} // new product
							
							demands.put(PP_MRP_ID, Qty);
							
							// If No Product Planning found, go to next MRP record 
							if (m_product_planning == null)
								continue;
							
							int daysPOQ = m_product_planning.getOrder_Period().intValueExact() - 1;
							//first DatePromised.compareTo for ORDER_POLICY_PeriodOrderQuantity
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()) 
									&& (DatePromisedTo !=null && DatePromised.compareTo(DatePromisedTo) > 0))
							{
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product ,DatePromisedFrom, trxName);						
								DatePromisedFrom = DatePromised;
								DatePromisedTo = TimeUtil.addDays(DatePromised, daysPOQ<0 ? 0 : daysPOQ);                                     
								POQDateStartSchedule = DatePromised;
								
							}
							else if(POQDateStartSchedule==null)
							{
								DatePromisedFrom = DatePromised;
								DatePromisedTo = TimeUtil.addDays(DatePromised, daysPOQ<0 ? 0 : daysPOQ);                                     
								POQDateStartSchedule = DatePromised;
							}
											
							//MRP-150
							//Past Due Demand
							//Indicates that a demand order is past due.
							if(DatePromised.compareTo(getToday()) < 0)
							{
								String comment = Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DatePromised)
												 + " : " + DatePromised;
								createMRPNote("MRP-150", AD_Org_ID, PP_MRP_ID, product, MPPMRP.getDocumentNo(PP_MRP_ID), 
										Qty, comment, trxName);
							}
		
							BeforePP_MRP_ID = PP_MRP_ID;
		
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
							{
								// Verify if is DatePromised < DatePromisedTo then Accumulation QtyGrossReqs 
								if (DatePromisedTo != null && DatePromised.compareTo(DatePromisedTo) <= 0)
								{
									QtyGrossReqs = QtyGrossReqs.add(Qty);
									log.info("Accumulation   QtyGrossReqs:" + QtyGrossReqs);
									log.info("DatePromised:" + DatePromised);
									log.info("DatePromisedTo:" + DatePromisedTo);
									Trx.get(trxName, true).commit(true);
									continue;
								}						
							}
							// If  Order_Policy = LoteForLote then always create new range for next period and put QtyGrossReqs          
							else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy()))
							{                                                                                                                                           
								QtyGrossReqs = QtyGrossReqs.add(Qty);
								BeforeDateStartSchedule = DatePromised; 		
								calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product,BeforeDateStartSchedule,trxName);
								Trx.get(trxName, true).commit(true);
								continue;
							}
							
							
							
						} // end while
		
						// If exist QtyGrossReq of last Demand after finish while verify plan
						if (QtyGrossReqs.signum() != 0 && product != null)
						{   
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy())
									&&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
							{
								BeforeDateStartSchedule =  POQDateStartSchedule; 
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule,trxName);
							}
							else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy())
									&& BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
							{
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule , trxName);
							}	
						}
						else if (product != null)
						{
							//Create Action Notice if exist supply
							getNetRequirements(
									AD_Client_ID, 
									AD_Org_ID, 
									M_Warehouse_ID, 
									product, 
									null, trxName);					
						}
						createMRPPegging(trxName);
						Trx.get(trxName, true).commit(true);
						DB.close(rs, pstmt);
					} // end for
				} // try
				catch (SQLException ex)
				{
					throw new DBException(ex);
				}
				finally {
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			 }
