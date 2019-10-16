  public List<String> getAvailabilityZones() {
    DescribeAvailabilityZonesResult res = ec2.describeAvailabilityZones();
    ArrayList<String> ret = new ArrayList<String>();
    for(AvailabilityZone z : res.getAvailabilityZones()) {
      ret.add(z.getZoneName());
    }
    return ret;
  }
