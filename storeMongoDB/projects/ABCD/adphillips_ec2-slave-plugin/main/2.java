  public List<String> getSecurityGroups() {
    DescribeSecurityGroupsResult res = ec2.describeSecurityGroups();
    ArrayList<String> ret = new ArrayList<String>();
    for(SecurityGroup s : res.getSecurityGroups()) {
      ret.add(s.getGroupName());
    }
    return ret;
  }
