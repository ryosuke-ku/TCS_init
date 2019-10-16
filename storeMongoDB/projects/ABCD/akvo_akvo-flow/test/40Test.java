    public void loadLots(HttpServletResponse resp, Integer numToCreate) {
        try {

            AccessPointHelper aph = new AccessPointHelper();

            ArrayList<AccessPoint> apList = new ArrayList<AccessPoint>();
            for (int j = 0; j < 1; j++) {

                for (int i = 0; i < numToCreate; i++) {
                    // double lon = 35 + (new Random().nextDouble() / new
                    // Random().nextInt(10));
                    // double lat = -15 + (new Random().nextDouble() / new
                    // Random().nextInt(10));

                    // ghana 7.9596438809,-1.20704621427
                    // double lon = -106;
                    // double lat = 39.1;
                    double lon = -68.1;
                    double lat = -16.4;
                    if (getRandomBoolean()) {
                        lon = lon
                                + (new Random().nextDouble() * new Random()
                                        .nextInt(10));
                    } else {
                        lon = lon
                                - (new Random().nextDouble() * new Random()
                                        .nextInt(10));

                    }

                    if (getRandomBoolean()) {
                        lat = lat
                                + (new Random().nextDouble() * new Random()
                                        .nextInt(10));
                    } else {
                        lat = lat
                                - (new Random().nextDouble() * new Random()
                                        .nextInt(10));
                    }
                    Calendar calendar = new GregorianCalendar(2010,
                            Calendar.JANUARY, 1);
                    Integer sign = null;
                    if (new Random().nextInt(3) % 2 == 0) {
                        sign = -1;
                    } else {
                        sign = 1;
                    }
                    calendar.add(Calendar.MONTH,
                            sign * new Random().nextInt(100));
                    log.info(i + ":");
                    AccessPoint ap = new AccessPoint();
                    ap.setLatitude(lat);
                    ap.setLongitude(lon);
                    ap.setCountryCode("BO");
                    if (getRandomBoolean()) {
                        ap.setWhoRepairsPoint("No One");
                    } else {
                        ap.setWhoRepairsPoint("Dru");
                    }

                    System.out
                            .println("AP: " + ap.getLatitude() + "/"
                                    + ap.getLongitude() + "Date: "
                                    + calendar.getTime());
                    // ap.setCollectionDate(calendar.getTime());
                    ap.setAltitude(0.0);
                    ap.setCommunityCode("test" + new Date());
                    ap.setCommunityName("test" + new Date());
                    ap.setPhotoURL("http://waterforpeople.s3.amazonaws.com/images/peru/pc28water.jpg");
                    ap.setImprovedWaterPointFlag(getRandomBoolean());
                    ap.setProvideAdequateQuantity(getRandomBoolean());
                    ap.setHasSystemBeenDown1DayFlag(getRandomBoolean());
                    ap.setMeetGovtQualityStandardFlag(getRandomBoolean());
                    ap.setMeetGovtQuantityStandardFlag(getRandomBoolean());
                    ap.setWaterForPeopleProjectFlag(getRandomBoolean());
                    ap.setWaterAvailableDayVisitFlag(getRandomBoolean());
                    ap.setEstimatedPeoplePerHouse(new Random().nextLong());
                    ap.setCollectTariffFlag(getRandomBoolean());
                    ap.setCurrentManagementStructurePoint("Community Board");
                    ap.setDescription("Waterpoint");
                    ap.setDistrict("test district");
                    ap.setEstimatedHouseholds(new Random().nextLong());
                    ap.setFarthestHouseholdfromPoint("Yes");
                    ap.setNumberOfHouseholdsUsingPoint(new Random().nextLong());
                    Integer year = new Random().nextInt(2011);
                    ap.setConstructionDateYear(year.toString());
                    ap.setCostPer(1.0);
                    ap.setCollectionDate(calendar.getTime());
                    calendar.add(Calendar.YEAR, -5);
                    ap.setConstructionDate(calendar.getTime());
                    ap.setPhotoName("Water point");
                    ap.setNumberOfHouseholdsUsingPoint(new Random().nextLong());
                    ap.setProvideAdequateQuantity(getRandomBoolean());
                    ap.setPpmFecalColiform(new Random().nextDouble());
                    ap.setNumberOfLitersPerPersonPerDay(new Random().nextInt());
                    ap.setExtimatedPopulation(new Random().nextLong());
                    ap.setPositiveBalance(getRandomBoolean());
                    ap.setFinancialRecordsAvailableDayOfVisitFlag(getRandomBoolean());
                    ap.setSparePartsOnHand(getRandomBoolean());
                    ap.setLocalSparePartsFlag(getRandomBoolean());
                    LocationType locationType = null;
                    Integer locationRandom = new Random().nextInt(3);
                    ap.setSystemExpansion("Yes");
                    if (locationRandom == 3)
                        locationType = LocationType.PERIURBAN;
                    else if (locationRandom == 1)
                        locationType = LocationType.RURAL;
                    else if (locationRandom == 0)
                        locationType = LocationType.URBAN;
                    else
                        locationType = LocationType.OTHER;

                    ap.setLocationType(locationType);
                    if (getRandomBoolean())
                        ap.setCurrentProblem("Yes");
                    ap.setPointType(AccessPoint.AccessPointType.WATER_POINT);
                    // if (getRandomBoolean())
                    // ap.setPointType(AccessPoint.AccessPointType.WATER_POINT);
                    // else
                    // ap.setPointType(AccessPoint.AccessPointType.PUBLIC_INSTITUTION);
                    if (getRandomBoolean())
                        ap.setTypeTechnologyString("Kiosk");
                    else
                        ap.setTypeTechnologyString("Afridev Handpump");

                    apList.add(ap);
                    if (i % 50 == 0)
                        log.log(Level.INFO, "Loaded to " + i);
                    aph.saveAccessPoint(ap);
                }
                resp.getWriter().println("About to save APs");

                // apDao.save(apList);
                resp.getWriter().println("Finished saving APs");

                // for(AccessPoint ap :apList){
                // CommunityLocationSummarizer cls = new
                // CommunityLocationSummarizer();
                // cls.performSummarization(String.valueOf(ap.getKey().getId()),
                // null, null, null, null);
                // MapSummarizer ms = new MapSummarizer();
                // }
            }
            resp.getWriter().println("Finished loading APs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
