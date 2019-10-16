    public Object execute() throws Exception {
        eventAdmin.sendEvent(new Event(topic, parse(properties)));
        return null;
    }
for (String key : event.getPropertyNames()) {
                if (!key.equals("event.topics") && !key.equals("timestamp")) {
                    out.println(key + ": " + getPrintValue(event, key));
                }
            }
            out.println();
            out.flush();
        }
    }
