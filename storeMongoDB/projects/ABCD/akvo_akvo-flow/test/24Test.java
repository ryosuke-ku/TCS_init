    public void loadWPDistanceTestData(HttpServletResponse resp) {
        try {
            AccessPointHelper aph = new AccessPointHelper();

            ArrayList<AccessPoint> apList = new ArrayList<AccessPoint>();
            for (int j = 0; j < 1; j++) {

                for (int i = 0; i < 100; i++) {
                    double lon = 35 + (new Random().nextDouble() / new Random()
                            .nextInt(10));
                    double lat = -15
                            + (new Random().nextDouble() / new Random()
                                    .nextInt(10));

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
                    ap.setCountryCode("US");

                    System.out
                            .println("AP: " + ap.getLatitude() + "/"
                                    + ap.getLongitude() + "Date: "
                                    + calendar.getTime());
                    // ap.setCollectionDate(calendar.getTime());
                    ap.setAltitude(0.0);
                    ap.setCommunityCode(String.valueOf(i));
                    ap.setCommunityName(String.valueOf(i));
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

                    if (getRandomBoolean())
                        ap.setCurrentProblem("Yes");

                    ap.setPointType(AccessPoint.AccessPointType.WATER_POINT);

                    if (getRandomBoolean())
                        ap.setTypeTechnologyString("Kiosk");
                    else
                        ap.setTypeTechnologyString("Afridev Handpump");

                    apList.add(ap);
                    if (i % 50 == 0)
                        log.log(Level.INFO, "Loaded to " + i);
                    aph.saveAccessPoint(ap);
                }
                resp.getWriter().println("Finished saving APs");
            }
            resp.getWriter().println("Finished loading APs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
