    public Object clone()
    {
        try
        {
            Option option = (Option) super.clone();
            option.values = new ArrayList<String>(values);
            return option;
        }
        catch (CloneNotSupportedException cnse)
        {
            throw new RuntimeException("A CloneNotSupportedException was thrown: " + cnse.getMessage());
        }
    }
