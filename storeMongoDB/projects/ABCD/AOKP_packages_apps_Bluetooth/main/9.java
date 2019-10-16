    public void addOriginator(String name, String[] phoneNumbers, String[] emailAddresses) {
        if(originator == null)
            originator = new ArrayList<vCard>();
        originator.add(new vCard(name, phoneNumbers, emailAddresses));
    }
