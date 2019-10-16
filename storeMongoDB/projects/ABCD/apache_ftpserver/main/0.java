    public Command getCommand(final String cmdName) {
        if (cmdName == null || cmdName.equals("")) {
            return null;
        }
        String upperCaseCmdName = cmdName.toUpperCase();
        return commandMap.get(upperCaseCmdName);
    }
          props.load(in);
            return props.getProperty("ftpserver.version");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read version", e);
        } finally {
            IoUtils.close(in);
        }
    }
