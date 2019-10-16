    public void addXml(Element element, long flags)
    { 
        // add All Options
        for(OptionEntry e:list){
            String value = String.valueOf(e.getValue());
            // Create Option Element
            Element opt = XMLUtil.addElement(element, "option", e.getText());
            opt.setAttribute("value", value);
        }
    }
